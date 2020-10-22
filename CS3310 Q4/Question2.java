//Tomes, Christopher
//Question2.java
//10/21/2020
import java.util.Random;


public class Question2 {

	public static void main(String[] args) throws Exception {
		Random rand = new Random();
	

	//	int numberOfFiles=100;
		int[] numFile = {10,100,1000,10000};      //10 files to compile, 100 files to compile etc.
		int sampleSize =10;                        //number of tests to perform to calc avg time.
	
		double[] avgTime = new double[sampleSize];  //AVERAGE TIME
	//	int numberOfDependencies=1;
		
		int[] numDepend = {2,10,100,1000}; //number of dependencies within a set correspinding to numFile size. ie 2 dependency in 10 files. 10 dependencies in size 1000.
		
		
		
		for(int jindex = 0; jindex < numFile.length; jindex++) //test each input size 10,100,1000...
		{
			 
		

			String[] files = new String[numFile[jindex]];    //Array of Names of Files
		
			String tempName;
		
		
			for(int index = 0;index < sampleSize; index++) 
			{
			
				for(int i = 0; i < numFile[jindex];i++)
				{//generate a random file name and store it into our set of Files to be built
					Character temp = (char)(rand.nextInt(26)+65);
					tempName = temp.toString();
					for(int j = 0;j<10;j++) 
					{
						temp = (char)(rand.nextInt(26)+65);
						tempName += temp.toString();
					}
					tempName += ".txt";
					files[i]=tempName;
				}
				node[] listOfFiles= new node[numFile[jindex]]; //build an array of objects to represent of files.
		
		
				for(int i = 0; i < numFile[jindex];i++) {
					listOfFiles[i]= new node(files[i], numFile[jindex]); //apply names to my custom objects.
				}
			
		
				int[][] filesDependencies = new int[numDepend[jindex]][2];
				for(int i = 0; i < numDepend[jindex];i++) 
				{//Randomly generate File Dependencies 
			
					filesDependencies[i][0]= rand.nextInt(numFile[jindex]);
					filesDependencies[i][1]= rand.nextInt(numFile[jindex]);
			
				}
				//timestamps
				long duration;
				long start = 0;
				long end = 0;
		
				start = System.nanoTime();//TimeStart.

	
				for(int i = 0; i < numDepend[jindex];i++) 
				{//store dependencies into our node classes
					listOfFiles[filesDependencies[i][0]].insert(listOfFiles[filesDependencies[i][1]]);
				}
				
				//classes now represent our files, lets attempt to actually build files.
				for(int i = 0; i < numFile[jindex];i++) 
				{
					try {
						if(!listOfFiles[i].built) 
						{
							build(listOfFiles[i]);//build file.
						}
					}
					catch(Exception e){
						System.out.println("Warning! a set of files could not be built. FILES_CONTAINS_CIRCULAR_DEPENDENCY.");
						break;
					}
				
				
				}
				end = System.nanoTime();//TimeStop.
				duration = end-start;
				avgTime[index]=(double)(duration/1000);
		}
	
	
		System.out.format("Set of files are built and average was generated. It took %f ms to build files of size %d \n", calcAvg(avgTime),numFile[jindex]);
		
		}
		
}
			
		
	
@SuppressWarnings("null")
public static void build(node n)throws Exception
{ 
	if(n.touched) //each node keeps track of itself in memory. If its been touched twice the program recognizes it is in a loop and throws an exception.
	{
		Exception e = null;
		throw e;
		
	}
	else 
	{
		n.touched = true; 
	}
	if(n.built) //node has been built already so return.
	{
		n.touched = false;
		return;
	}
	else if(n!=null){//if the node exists check its dependencies.
		for(int i = 0; n.dependencies[i]!= null; i++) 
		{
			try{
				build(n.dependencies[i]);//build the dependeny first.
			}catch(Exception e) {
				throw e;
			}
			
		}
		//System.out.format("Building %s \n",n.name);  Dont do anything to build files. Represented as print to screen
		n.built = true;//BUILD
	}
	n.touched = false;
	return;
}


private static double calcAvg(double[] avgTime) {
	double avg=0;
	for(int i=0;i<avgTime.length;i++) {
		avg +=avgTime[i];
	}
	return avg/avgTime.length;	
}
}
