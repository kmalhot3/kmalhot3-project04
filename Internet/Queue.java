import java.util.Iterator;

public class Queue<Type> implements Iterable {

   private String name;
   private Node head, tail;
   private int size;

   //basic constructor for the queue
   public Queue (String name){
     // System.out.println("Constructing queue");
      this.name = name;
      this.size = 0;
   }

   //front of the list = end of the queue
   public boolean enqueue(Type data){
      //System.out.println("Enqueue");
      if(this.isEmpty()){
         Node newNode = new Node(data);
         this.head = newNode;
         this.tail = newNode;
         this.size++;
         return true;
      }

     this.head = this.head.insert(data);
     this.size++; 

      return true;
   }

   public Type dequeue(){
	   
       
      if((this.tail == this.head) && (this.head!=null))
      {
		  this.tail = null;
		  this.head= null;
		
		  this.size--;
		
		  return head.getData();
	  }
	  
	  if(this.tail ==null)
	  {
		
		  return head.getData();
	  }
	  
	  Node temp = this.head;
      while(temp.next!= this.tail)
      {
         temp = temp.next;
      }
      this.tail = temp;
      
      temp = temp.next;
      this.tail.next = null;
      
      this.size--;
      
      return temp.getData();

   }

   public boolean isEmpty(){
      return this.size == 0;
   }

   //returns the current size of the queue
   public int size(){
      
      return this.size;
   }

   // console display for testing
   public String toString(){
      Node current = this.head;
      String retString;
      retString ="[ ";
      for(int i = 0 ; i < this.size; i++){
         if(i != 0){
            retString += ",";
         }
         retString += current.getData();

         current = current.getNext();
      }
      retString += " ] ";

      return retString;
   }

   public Type peek(){
      if(this.size == 0){
         return null;
      }
      return this.tail.getData();
   }

   public String getName(){
      return this.name;
   }



   private class Node {
      // data
      private Node next;
      private Type data;

      // constructors
      public Node(Type data){
         this.next = null;
         this.data = data;
      }

      public Node(Type data, Node next) {
         this.next = next;
         this.data = data;
      }

      // inserts new nodes to the front of the list aka the top of the stack
      public Node insert(Type data) {
         return new Node(data,this);
      }

      // returning the next member
      public Node getNext() {
         return next;
      }

      // helps with peek
      public Type getData() {
         return this.data;
      }

      public String toString(){
         return this.data.toString();
      }

   }




   public java.util.Iterator<Type> iterator(){
      return new QueueIterator();
   }

   private class QueueIterator implements Iterator<Type>{

      private Node currentNode;

      public QueueIterator(){
         currentNode = head;
      }

      public boolean hasNext() {
         return currentNode == null;
      }

      public Type next() {
         if(!hasNext()){
            System.out.println("Stack Empty");
            return null;
         }
         Type temp = currentNode.getData();
         currentNode = currentNode.next;
         return temp;
      }

   }

}
