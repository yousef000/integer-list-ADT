//-----------------------------------------------------------------------------------------
// List.java
// Yousef Dost
// ydost@ ucsc.edu
// PA1
// An integer List ADT
//-----------------------------------------------------------------------------------------

public class List{
    private class Node{
        int data;
        Node next;
        Node previous;
        Node(int data){
            next = previous = null;
            this.data = data;
            
        }
        public String toString(){
            return String.valueOf(data);
        }
        // equals(): overrides Object's equals() method
        public boolean equals(Object x){
            boolean eq = false;
            Node that;
            if(x instanceof Node){
                that = (Node) x;
                eq = (this.data==that.data);
            }
            return eq;
        }
        
        
    }
    
    private Node front;         //root Node
    private Node back;
    private Node cursor;
    private int length;
    private int index;
    List(){
        front = null;
        back= null;
        cursor = null;
        length = 0;
        index = -1;
    }
    
    //Access Functions
    int length(){
        return length;
    }
    int index(){
        if(cursor == null)
            return -1;
        else{
            //return cursor.element.index;
            return index;
        }
        
    }
    int front(){
        if(this.isEmpty())
            throw new RuntimeException("front() called on empty List");
        else{
            return front.data;
        }
        
    }
    int back(){
        if(this.isEmpty())
            throw new RuntimeException("back() called on empty List");
        else{
            return back.data;
        }
        
    }
    // returns cursor element. Pre: length() > 0, index() >= 0
    int get(){
        if(this.isEmpty())
            throw new RuntimeException("get() called on empty List");
        else if(index < 0)
            throw new RuntimeException("get() called on undefined index");
        else{
            return cursor.data;
        }
        
    }
    boolean equals(List L){
        if(length != L.length){
            return false;
        }
        else{
            Node tempNode = front;
            boolean Equals = true;
            L.moveFront();
            while(tempNode != null && Equals == true){
                if(tempNode.data != L.get())
                    Equals = false;
                L.moveNext();
                tempNode = tempNode.next;
            }
            return Equals;
        }
        
    }
    boolean isEmpty(){
        return length == 0;
        
    }
    //Manipulation procedures
    
    // Resets this List to its original empty state.
    void clear(){
        front = back = null;
        length = 0;
        cursor = null;
    }
    // If List is non-empty, places the cursor under the front element,
    // otherwise does nothing.
    void moveFront(){
        if(!this.isEmpty()){
            cursor = front;
            index = 0;
        }
    }
    // If List is non-empty, places the cursor under the back element,
    // otherwise does nothing.
    void moveBack(){
        if(this.isEmpty())
            throw new RuntimeException("moveBack() called on empty List");
        else{
            cursor = back;
            index = length-1;
        }
    }
    // If cursor is defined and not at front, moves cursor one step toward front of this List, if cursor is defined and at front, cursor becomes // undefined, if cursor is undefined does nothing.
    void movePrev(){
        if(cursor != front && cursor != null){
            cursor = cursor.previous;
            index--;}
        else if(cursor == front && cursor != null)
            cursor = null;
        else{
            throw new RuntimeException("movePrev() called on undefined cursor");
        }
        
    }
    // If cursor is defined and not at back, moves cursor one step toward // back of this List, if cursor is defined and at back, cursor becomes // undefined, if cursor is undefined does nothing.
    void moveNext(){
        if(cursor != back && cursor != null){
            cursor = cursor.next;
            index++;}
        else if(cursor == back && cursor != null)
            cursor = null;
        else{
            throw new RuntimeException("moveNext() called on undefined cursor");
        }
    }
    // Insert new element into this List. If List is non empty
    // insertion takes place before front element.
    void prepend(int data){
        if(this.isEmpty()){
            Node temp = new Node(data);
            front = back = temp;
        }
        else{
            Node temp = new Node(data);
            temp.next = front;
            front.previous = temp;
            front = temp;
            
        }
        length++;
        index++;
    }
    // Insert new element into this List. If List is non empty
    // insertion takes place after back element.
    void append(int data){
        if(length <= 0){
            Node temp = new Node(data);
            front = back = temp;
        }
        else{
            Node temp = new Node(data);
            back.next = temp;
            temp.previous = back;
            back = temp;
            
        }
        length++;
        
    }
    // Insert new element before cursor.
    // Pre: length()>0, index()>=0
    void insertBefore(int data){
        if(this.isEmpty())
            throw new RuntimeException("insertBefore() called on empty List");
        if(index < 0)
            throw new RuntimeException("insertBefore() called on undefined index");
        else if(cursor.previous == null){     //Node = front
            this.prepend(data);
        }
        else{
            Node temp = new Node(data);
            cursor.previous.next = temp;
            temp.previous = cursor.previous;
            temp.next = cursor;
            cursor.previous = temp;
            index++;
            length++;
        }
        
        
    }
    // Inserts new element after cursor.
    // Pre: length()>0, index()>=0
    void insertAfter(int data){
        if(this.isEmpty())
            throw new RuntimeException("insertAfter() called on empty List");
        if(index == -1)
            throw new RuntimeException("insertAfter() called on undefined index");
        if(cursor == back || length == 1){
            Node temp = new Node(data);
            cursor.next = temp;
            temp.previous = cursor;
            back = temp;
        }
        else{
            Node temp = new Node(data);
            cursor.next.previous = temp;
            temp.next = cursor.next;
            temp.previous = cursor;
            cursor.next = temp;
            
        }
        length++;
    }
    // Deletes the back element. Pre: length()>0
    void deleteFront(){
        if(this.isEmpty())
            throw new RuntimeException("deleteFront() called on empty List");
        else{
            if(cursor == front)
                cursor = null;
            
            if(length > 1){
                front = front.next;
                front.previous = null;
            }
            else{
                front = back = null;}
            
            length--;
            index--;
        }
        
    }
    // Deletes the back element. Pre: length()>0
    void deleteBack(){
        if(this.isEmpty())
            throw new RuntimeException("deleteBack() called on empty List");
        else if(length == 1){
            front = back = null;
        }
        else{
            if(cursor == back)
                cursor = null;
            back = back.previous;
            back.next = null;
            
        }
        length--;
        
    }
    // Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0
    void delete(){
        if(this.isEmpty())
            throw new RuntimeException("delete() called on empty List");
        if(index < 0)
            throw new RuntimeException("delete() called on undefined index");
        else if(length == 1){
            front = back = cursor = null;
            length--;
        }
        else if(cursor == front)
            this.deleteFront();
        else if(cursor == back)
            this.deleteBack();
        else{
            cursor.previous.next = cursor.next;
            cursor.next.previous = cursor.previous;
            cursor = null;
            length--;
        }
        index = -1;
    }
    // Returns a new List representing the same integer sequence as this // List. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.
    
    List copy(){
        List tempList = new List();
        Node tempNode = front;
        while(tempNode != null){
            tempList.append(tempNode.data);
            tempNode = tempNode.next;
        }
        return tempList;
    }
    // Other methods
    // Overrides Object's toString method. Returns a String
    // representation of this List consisting of a space
    // separated sequence of integers, with front on left.
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N = front;
        while(N!=null){
            sb.append(N.toString());
            sb.append(" ");
            N = N.next;
        }
        return new String(sb);
    }
    
    
    
}

