import java.util.*;
class Node{
    int key;
    int value;
    Node prev,next;
    public  Node(int key,int value)// Constructor to initialize node
    {
        this.key = key;
        this.value =value;
    }
}
class DoublyLinkedList{
    private Node head, tail;
    public DoublyLinkedList()// Constructor to Doubly Linked List
    {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    public void add_To_Beg(Node node)// Method to add node at beginning
    {
        node.prev =head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    public void remove_Node(Node node)// Method to remove a node
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void move_To_Beg(Node node)// Method to move a node to beginning
    {
        remove_Node(node);
        add_To_Beg(node);
    }
    public Node remove_From_Tail()// Method to remove element from last
    {
        Node node = tail.prev;
        remove_Node(node);
        return node;
    }
}
class LRUCache{
    private int capacity;
    private HashMap<Integer,Node> cache;
    private DoublyLinkedList dll;
    private int size;
    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList();
        this.size = 0;
    }
    public int get(int key)
    {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            dll.move_To_Beg(node);
            return node.value;
        }
        return -1;
    }
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            dll.move_To_Beg(node);
        } else {
            if (size == capacity) {
                Node lruNode = dll.remove_From_Tail();
                cache.remove(lruNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            dll.add_To_Beg(newNode);
            cache.put(key, newNode);
            size++;
        }
    }
    public static void main(String args[])//Main method
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the required size of cache :");
        int cache_size =sc.nextInt();
        LRUCache lruCache = new LRUCache(cache_size);
        int choice =0;
        do{
            System.out.println("------------------------------------------------------------");
            System.out.println("0].Exit\n1].Insert Key.\n2].Get the Key.");
            System.out.println("------------------------------------------------------------");
            System.out.println("Enter the choice :");
            choice = sc.nextInt();
            switch(choice)
            {
              case 0:
              {
                System.out.println("Exiting...");
                break;
              }
              case 1:
              {
                System.out.println("Enter unique key and value : ");
                int key = sc.nextInt();
                int value = sc.nextInt();
                lruCache.put(key,value);
                break;
              } 
              case 2:
              {
                System.out.println("Enter key to Search in cache : ");
                int key = sc.nextInt();
                int value = lruCache.get(key);
                if(value == -1)
                {
                    System.out.println("Key Not Found...");
                }
                else{
                    System.out.println("Value for key "+key+" is "+value);
                }
                break;
              }
              default :
              {
                System.out.println("Enter Appropriate choice...[0,1,2]");
              }
            }
        }while(choice != 0);
    }
}
