import java.io.File;

public class Module_1_Assignment

{

     static void RecursivePrint(File[] arr,int index,int level)

     {

         if(index == arr.length)

             return;

         for (int i = 0; i < level; i++)

             System.out.print("\t");

         if(arr[index].isFile())

             System.out.println(arr[index].getName());        

         else if(arr[index].isDirectory())

         {

             System.out.println("[" + arr[index].getName() + "]");             

             RecursivePrint(arr[index].listFiles(), 0, level + 1);

         }          

         RecursivePrint(arr,++index, level);

    }     

    public static void main(String[] args)

    {

        String testingdirectorypath = "C:\\Users\\Richa\\Desktop\\cen4025\\Module_1_Assignment";

        File testingdirectory = new File(testingdirectorypath);

         if(testingdirectory.exists() && testingdirectory.isDirectory())

        {

         File arr[] = testingdirectory.listFiles();       

            System.out.println("*********************************************************");

            System.out.println("Files from main directory : " + testingdirectory);

            System.out.println("*********************************************************");

            RecursivePrint(arr,0,0);

       }

    }

}