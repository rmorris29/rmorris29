'use strict';

/**
 * class representing board of game (othello)
 *
 * @class
 */
class Board {

    /**
     * constructor
     *
     * @param {number} [width=8] - Width of board
     */
    constructor(width = 8) {

        this.width = width;

        /*
         * All cells will store in an object with a key that generated by x,y coordinates of cell.
         * ex. cell with coordinates [1, 2] will have a key like "1-2".
         */
        this.cells = {};
        this.moves = [];
        this.turn = "black";
        this._finished = false;


        // initialize the board
        this.initBoard();
    }

    /**
     * status of the game
     * This will return true if the game has been finished.
     *
     * @returns {boolean}
     */
    get finished() {
        return this._finished
    }

    /**
     * This method will initialize the board
     *
     */
    initBoard() {
        for (let x = 0; x < this.width; x++) {
            for (let y = 0; y < this.width; y++) {
                this.cells[`${x}-${y}`] = new Cell(x, y);
            }
        }

        // put initial nuts (4 of them)
        this.putInitialNuts();
    }

    putInitialNuts() {
        const centerCord = Math.floor(this.width / 2);

        this.setOwner(centerCord, centerCord, "white");
        this.setOwner(centerCord - 1, centerCord - 1, "white");

        this.setOwner(centerCord, centerCord - 1, "black");
        this.setOwner(centerCord - 1, centerCord, "black");

        this.findMoves();
    }

    /**
     * Get specific cell of the board by coordinates.
     *
     * @param x
     * @param y
     * @returns {*}
     */
    getCell(x, y) {
        return this.cells[`${x}-${y}`]
    }

    /**
     * Board has two players, white and black.
     * this method will change the turn between each player
     */
    changeTurn() {
        this.turn = this.turn === "white" ? "black" : "white";
    }

    /**
     * Set owner of specific cell if the cell is exists.
     *
     * @param {number} x
     * @param {number} y
     * @param {string|null} owner
     */
    setOwner(x, y, owner) {
        if (!this.getCell(x, y)) {
            throw new Error(`Could not found any cell for x:${x} & y:${y}`)
        }

        this.getCell(x, y).owner = owner;
    }

    /**
     * @param {string|null} owner - To specify the owner of the needed cells
     * @returns {Array}
     */
    getNuts(owner = null) {
        let nuts = [];
        Object.keys(this.cells)
            .map(key => {
                if ((!owner && this.cells[key].owner !== null) || (owner && this.cells[key].owner === owner))
                    nuts.push(this.cells[key]);
            });
        return nuts;
    }

    /**
     * Return all white nuts that are placed on the board.
     *
     * @returns {Array}
     */
    getWhiteNuts() {
        return this.getNuts("white");
    }

    /**
     * Return all black nuts that are placed on the board.
     *
     * @returns {Array}
     */
    getBlackNuts() {
        return this.getNuts("black");
    }

    /**
     * Add a cell as valid move for a player
     *
     * @param {number} x - Cell x coordinates
     * @param {number} y - Cell y coordinates
     * @param {array} centerNut - To specify that which nut has responsible for the move
     * @param {string} direction - To specify the cross direction for the move
     */
    addMove(x, y, centerNut, direction) {

        let alreadyAdded = false;
        this.moves.map((move) => {
            if (move.x === x && move.y === y) {
                alreadyAdded = true;

                if (move.directions.indexOf(direction) === -1) {
                    move.directions.push(direction)
                }
            }
        });

        if (!alreadyAdded)
            this.moves.push({
                x,
                y,
                centerNut,
                directions: [direction],
            })
    }

    /**
     * Check if a coordinate is on board or not
     *
     * @param {number} x
     * @param {number} y
     * @returns {boolean}
     */
    isOnBoard(x, y) {
        return (x > -1 && y > -1) && (x < this.width && y < this.width)
    }

    /**
     * Get coordinate of empty neighbors of a cell
     *
     * @param {number} x
     * @param {number} y
     * @returns {Array}
     */
    getEmptyNeighborsOfCell(x, y) {
        let emptyNeighbors = [];

        const cellNeighbors = this.getCell(x, y).neighbors();
        cellNeighbors.map(([nx, ny]) => {
            if (this.isOnBoard(nx, ny) && this.getCell(nx, ny).owner === null) {
                emptyNeighbors.push(this.getCell(nx, ny));
            }
        });

        return emptyNeighbors
    }

    /**
     * extreme maths, thank you to my mentor Roderic for his guidance here
     *
     * @param cx
     * @param cy
     * @returns {object}
     */
    crossAxises(cx, cy) {
        return {
            topLeftToCell: (i) => `${cx + i}-${cy + i}`,        // x > cx && y > cy
            topCenterToCell: (i) => `${cx}-${cy + i}`,          // x === cx && y > cy
            topRightToCell: (i) => `${cx - i}-${cy + i}`,       // x < cx && y > cy
            leftCenterToCell: (i) => `${cx + i}-${cy}`,         // x > cx && y === cy
            rightCenterToCell: (i) => `${cx - i}-${cy}`,        // x < cx && y === cy
            bottomLeftToCell: (i) => `${cx + i}-${cy - i}`,     // x > cx && y < cy
            bottomCenterToCell: (i) => `${cx}-${cy - i}`,       // x < cx && y < cy
            bottomRightToCell: (i) => `${cx - i}-${cy - i}`,    // x < cx && y < cy
        };
    }

    /**
     * Test all cross directions to a center cell (from neighbors) and find valid moves
     *
     * @param {number} cx
     * @param {number} cy
     * @param {array} centerNut - To determine that which cell is the center cell
     */
    crossAllDirectionsToCell(cx, cy, centerNut) {

        const directions = this.crossAxises(cx, cy);

        Object.keys(directions).map(dir => {
            let rivalNutsOnCross = 0;
            for (let i = 0; i < this.width; i++) {

                let cell = this.cells[directions[dir](i)];
                if (!cell) break;


                if (cell.owner !== this.turn && cell.owner !== null) {
                    rivalNutsOnCross++;

                } else if (cell.owner === this.turn && rivalNutsOnCross > 0) {
                    this.addMove(cx, cy, centerNut, dir);
                    break;

                } else if (i === 1 && (cell.owner === this.turn || cell.owner === null)) {
                    break;

                } else if (rivalNutsOnCross > 0 && cell.owner === null) {
                    break;
                }
            }
        })

    }

    /**
     * Find all possible moves of the board for current turn
     */
    findMoves() {
        this.moves = [];

        let nuts;
        if (this.turn === "white")
            nuts = this.getBlackNuts();
        else
            nuts = this.getWhiteNuts();


        nuts.map((nut) => {
            const [x, y] = nut.pos();
            const neighbors = this.getEmptyNeighborsOfCell(x, y);

            neighbors.map((neighbor) => {
                const [nx, ny] = neighbor.pos();

                this.crossAllDirectionsToCell(nx, ny, [x, y]);
            });

        });

    }

    /**
     * Find all cells that must flip on a placement
     *
     * @param {number} nx - The X coordinate of the flipping cell
     * @param {number} ny - The Y coordinate of the flipping cell
     * @param {string} dir
     * @returns {Array}
     */
    findCellsToFlip(nx, ny, dir) {

        const directions = this.crossAxises(nx, ny);

        let cellToChange = [];

        for (let i = 0; i <= this.width; i++) {
            let cell = this.cells[directions[dir](i)];

            if (!cell) {
                break;

            } else if (cell.owner !== this.turn) {
                cellToChange.push(cell);

            } else if (cell.owner === this.turn || cell.owner === null) {
                break;

            }
        }

        return cellToChange
    }

    /**
     * Check move validity for a coordinate
     *
     * @param {number} x
     * @param {number} y
     * @returns {boolean}
     */
    isValidMove(x, y) {
        return this.moves.find(move => move.x === x && move.y === y) !== undefined
    }

    /**
     * This method will call when player wants to place nut on a cell.
     *
     * @param {number} x
     * @param {number} y
     */
    placeNutTo(x, y) {

        if (!this.isValidMove(x, y))
            throw new Error(`The move [${x},${y}] is impossible!`);

        const move = this.moves.find(move => move.x === x && move.y === y);

        let cellsToFlip = [];
        move.directions.map((dir) => {
            cellsToFlip = [...cellsToFlip, ...this.findCellsToFlip(x, y, dir)];
        });

        // flip nuts of all directions together
        cellsToFlip.map(cell => cell.owner = this.turn);

        // change turn after each placement
        this.changeTurn();
        this.findMoves();

        // check valid moves after each placement
        // and change the turn again when moves are empty
        if (this.moves.length === 0) {
            this.changeTurn();
            this.findMoves();

            // check the moves length again and set the finish status as true if it is empty
            if (this.moves.length === 0)
                this._finished = true;
        }

    }

    /**
     * This will return a result for board as object.
     *
     * @returns {{winner: string, white: number, black: number}}
     */
    gameResult() {
        const white = this.getWhiteNuts().length,
            black = this.getBlackNuts().length;

        return {
            white,
            black,
            winner: white > black ? "white" :
                black > white ?
                    "black" : "equal"
        };

    }
}