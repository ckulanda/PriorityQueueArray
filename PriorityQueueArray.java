import java.util.PriorityQueue;

//type casted to T because we dont know data type
public class PriorityQueueArray<T> {
	//do we need 2 queues? 1 for normal 1 for sort?
	//have to be arrays because were implementing the array 
	private T[] normalQueue;
	private int[] priorityQueue;
	
	public int backIndex;
	
	
	public PriorityQueueArray() 
	{
		//one less than the front so negative one
		backIndex=-1;
		T[]originalQueue=(T[])new Object[2];
		normalQueue=originalQueue;
		//has to be a number because ranking will be number
		//based in the end 
		int[] tempPriority =new int[2];
		priorityQueue=tempPriority;
	}
	public void add(T newEntry, int priority)
	{
		//check to see if theres room
		ensureCapacity();
		int location=addLocation(priority);
		
		makeRoom(location);
		
		//add the new entry and made sure you
		//call the addLocation so it knows which "slot"
		//to be placed into number wise
		normalQueue[location]=newEntry;
		priorityQueue[location]=priority;
	}
	public T peek()
	{
		T front=null;
		if(!isEmpty())
		{
			front=normalQueue[backIndex];	
		}
		return front;
	}
	public T remove()
	{
		T value=null;
		if(!isEmpty())
		{
			//doing the same as peek but reducing the index
			//because its removing the physical entry
			//and not just "looking" at it
			value=normalQueue[backIndex];
			backIndex--;
		}
		return value;
	}
	private void ensureCapacity()
	{
		if(normalQueue.length==backIndex+1)
		{
			T[] oldQueue=normalQueue;
			int[] oldPriority= priorityQueue;
			int olderSize=oldQueue.length;
			
			//this will be our new queue and will make it larger
			T[] tempoQueue=(T[]) new Object[2*olderSize];
			normalQueue=tempoQueue;
			int[] tempoPriority=new int[2*olderSize];
			priorityQueue=tempoPriority;
		
			
			for(int i=0; i<olderSize; i++)
			{
				//irterate along the entire que
				//ensuring that the old and original
				//are matched to the same location
				normalQueue[i]=oldQueue[i];
				priorityQueue[i]=oldPriority[i];
				
			}
		}
	}
	
	public int addLocation(int priority)
	{
		
		for(int i=backIndex; i>=0; i++)
		{
			if(priorityQueue[i]<priority)
				return i;
		}
		return backIndex+1;
	}
	//	makes space incase theres already a entry in the
	//location given by the location method
	public void makeRoom(int location)
	{
		//if that location isnt in the back
		//shift to the next index to create space
		if(location!=backIndex+1)
			for(int i=backIndex; i>=location; i++)
			{
				normalQueue[i+1]=normalQueue[i];
				priorityQueue[i+1]=priorityQueue[i+1];
			}
		//continue iterating through the back
		backIndex++;
	}
	public boolean isEmpty()
	{
		//ensures the queue isnt empty
		if(backIndex<0)
		{
			backIndex=-1;
			System.out.println("Queue is Empty");
			return true;
		}
		return false;
	}

	public static void main(String[] args) 
	{
		

	}

}
