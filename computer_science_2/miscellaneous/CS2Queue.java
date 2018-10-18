public class CS2Queue{
   private String[] queue;
   private int rear;
 
   //------------------------------------------------------------------------//
   // Constructors                                                           //
   //------------------------------------------------------------------------//
   public CS2Queue () {
      /*
      front = -1;
      rear = -1;
      */
      rear = -1;
      queue = new String[10];
   
   } 
   
   //------------------------------------------------------------------------//
   public CS2Queue(int size) { 
      rear = -1;
      queue = new String[size];
   
   }
    
   //------------------------------------------------------------------------//
   // Public methods                                                         //
   //------------------------------------------------------------------------//
   public boolean isFull() {
      if (rear == queue.length-1) {
      
         return(true);
      }
      return(false);
   }
   
   //------------------------------------------------------------------------//
   public boolean isEmpty() {
      if(rear == -1) {
         return(true);
      }
      return(false);
   }
   
   //------------------------------------------------------------------------//
   private boolean isSingleton() {
      if(rear == 0) {
         return(true);
      }
      return(false);
      
   }
   
   //------------------------------------------------------------------------//
   public boolean enqueue(String data) {
      // Fail silently if queue is full
      if(!isFull()) {
         queue[++rear] = data;
         return(true);
      }
      
      return(false);
   }
   
   //------------------------------------------------------------------------//
   public boolean dequeue() {
      if(!isEmpty()) {
         for(int i = 1; i < rear + 1; ++i) {
            queue[i-1] = queue[i];
            queue[i] = null;
         }
         
         --rear;
         return(true);
      }
      return(false);
   }
   public void dataDump() {
      String str = "";
      for(int i = 0; i < rear + 1; ++i) {
         str = "" + str + queue[i];
      }
      System.out.println(str);
   }
   
}

