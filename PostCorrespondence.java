package PCP.main;
import java.util.*;

import PCP.components.*;

class PostCorrespondence {
    public static Deque<Node> frontier;
    public static HashMap<String, String> visited;
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    public static final int LIMIT_REACHED = -1;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter max size of queue");
        int maxSizeQueue = scan.nextInt();
        System.out.println("Enter max size of depth");
        int maxDepth = scan.nextInt();
        //System.out.println(maxDepth);
        System.out.println("Enter flag");
        int flag = scan.nextInt();
        System.out.println("Enter number of domino");
        int numDomino = scan.nextInt();

        ArrayList<Node> nodeList = new ArrayList<>();

        ArrayList<String> topList = new ArrayList<>();
        ArrayList<String> bottomList = new ArrayList<>();

        scan.nextLine();
        System.out.println("Enter your domino(s):");
        for (int i=1; i <= numDomino; i++){
            String d = scan.nextLine();
            String[] data = d.split(" ");
            
            topList.add(data[1]);
            bottomList.add(data[2]);

            String dominoName = "D" + i;
            Node node = new Node(data[1], data[2], dominoName);
            nodeList.add(node);
            
        }
        boolean printStates = false;
        if (flag == 1){
            printStates = true;
        }

        frontier = new LinkedList<>();
        visited = new HashMap<>();

        boolean foundSolution = false;

        boolean solutionBDF = PostCorrespondence.solveBDF(printStates, maxSizeQueue, nodeList, frontier, visited);
        
        if (solutionBDF){
            foundSolution = true;
            
        }
        else{
            int solutionIDDFS = PostCorrespondence.solveIDFS(printStates, maxDepth, nodeList, frontier, visited);
            if (solutionIDDFS == SUCCESS){
                
                foundSolution = true;
            }
        }
        if (!foundSolution){
            System.out.println("No solution exists.");
        }
        
        scan.close();
    }
    
    public static boolean solveBDF(boolean printStates, int maxSizeQueue, ArrayList<Node> nodeList, Deque<Node> frontier, HashMap<String, String> visited){
        if (printStates){
            System.out.println("----- Filling Frontier -----");
        }

        Node initialNode = new Node("","", null); 

        frontier.add(initialNode);
        visited.put(initialNode.getTop(), initialNode.getBottom());

        while (!frontier.isEmpty() && frontier.size() < maxSizeQueue){
            Node currNode = frontier.remove();

            if (!currNode.isRootNode()){
                
                if (printStates){
                    String state = currNode.getDifference();
                    System.out.println("Removing State From Frontier: " + state);
                }
                
            }
            
            for (int i=0; i < nodeList.size(); i++){
                
                String newTop = new String("");
                String newBottom = new String("");

                Node newChildNode; 

                //check if currNode is root:
                if (currNode.isRootNode()){
                    newTop = nodeList.get(i).getTop();
                    newBottom = nodeList.get(i).getBottom();
                   newChildNode = new Node(newTop, newBottom, nodeList.get(i).getDominoName());

                }
                else{
                    newTop = currNode.getTop() + nodeList.get(i).getTop();
                    newBottom = currNode.getBottom() + nodeList.get(i).getBottom();
                    newChildNode = new Node(newTop, newBottom, currNode.getDominoName()+ ","+nodeList.get(i).getDominoName());

                }

                //check if valid node
                if (newChildNode.isValidNode()){
                    //check if visited node
                    if (!newBottom.equals(visited.get(newTop))){
                        if (newChildNode.isSolutionNode()){
                            System.out.println("Solution Size (# of dominoes): "+ newChildNode.getSize() +" (Solution found in Stage 1)");

                            System.out.println("The solution sequence is: "+ newChildNode.getDominoName());
                            System.out.println("The top and bottom of the sequence looks like: " + newChildNode.getTop() + "/" + newChildNode.getBottom());
 
                            return true;
                        }

                        visited.put(newTop, newBottom);
                        frontier.add(newChildNode);

                        if (printStates){
                            String state = newChildNode.getDifference();
                       
                            System.out.println("Adding State to Frontier: " + state);
                        }
                        
                        
                    }
                }
            }
       
        }
        
        System.out.println("----- Frontier Complete -----");
        return false;
    }
    


    public static int recursiveDLS(boolean printStates, Node initialNode, int limit, ArrayList<Node> nodeList, Deque<Node> frontier, HashMap<String, String> visited){
        Stack<Node> updatedFrontier = new Stack<Node>();
        //Deque<Node> updatedFrontier = frontier;
        HashMap<String, String> updatedVisited = new HashMap<>();
        updatedFrontier.push(initialNode);
        
        while (!updatedFrontier.isEmpty()){

            Node currNode = updatedFrontier.pop();
            

            if (currNode.isSolutionNode()){
                if (printStates){
                    System.out.println("Popping State from Stack: ");
                    System.out.println("----- Part 2 (Iterative Deepening) Ending -----");
                }
                
                System.out.println("Solution Size (# of dominoes): "+ currNode.getSize() +" (Found at iterative deepening with depth: " + currNode.getDepth()+  ")");
                
                System.out.println("The solution sequence is: "+ currNode.getDominoName());
                System.out.println("The top and bottom of the sequence looks like: " + currNode.getTop() + "/" + currNode.getBottom());

                return SUCCESS;
            }
            
            if (currNode.getDepth() > limit){
                return LIMIT_REACHED;
            }
            else{
               // System.out.println("no "+currNode.getDifference()+ " " +currNode.getDepth()+" "+limit);
                if (printStates){
                    System.out.println("Popping State from Stack: "+ currNode.getDifference());
                } 

            for (int i=0; i < nodeList.size(); i++){
                if (currNode.getDepth() > limit){
                    return LIMIT_REACHED;
                }
                
                
                String newTop = new String("");
                String newBottom = new String("");

                //check if currNode is root:
                if (currNode.isRootNode()){
                    newTop = nodeList.get(i).getTop();
                    newBottom = nodeList.get(i).getBottom();
                }
                else{
                    newTop = currNode.getTop() + nodeList.get(i).getTop();
                    newBottom = currNode.getBottom() + nodeList.get(i).getBottom();
                }

                Node newChildNode = new Node(newTop, newBottom, currNode.getDominoName()+ ","+nodeList.get(i).getDominoName());

                //check if valid node
                if (newChildNode.isValidNode()){
                    //check if visited node
                    if (!newBottom.equals(updatedVisited.get(newTop))){
                        newChildNode.setDepth(currNode.getDepth()+1);

                        //updatedVisited.put(newTop, newBottom);
                        updatedFrontier.add(newChildNode);
                    }
                }
            }
        }
        }   
        return FAILURE;   
    }

    public static int solveIDFS(boolean printStates, int maxDepth, ArrayList<Node> nodeList, Deque<Node> frontier, HashMap<String, String> visited){
        if (printStates){
            System.out.println("----- Part 2 (Iterative Deepening) Starting -----");
        }
        boolean foundSolution = false;
        for (int i=0; i <= maxDepth; i++){
            
            Deque<Node> updatedFrontier = new LinkedList<>(frontier);

            if (printStates){
                System.out.println("Starting DFS with depth set to: "+ i);
            }
            while (!updatedFrontier.isEmpty()){
                Node initialNode = updatedFrontier.pop();
                if (printStates){
                    String state = initialNode.getDifference();
                    System.out.println("Start State: " + state);
                }

                int result = PostCorrespondence.recursiveDLS(printStates, initialNode, i, nodeList, updatedFrontier, visited);
            
                if (result==SUCCESS){
                    foundSolution = true;
                    break;
                }
            }
            if (foundSolution){
                break;
            }
            //break;
        }
        if (foundSolution){
            return SUCCESS;
        }
        return FAILURE;
    }
}