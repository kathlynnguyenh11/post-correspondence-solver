package PCP.components;

import java.util.*;


public class Node {
    private String top;
    private String bottom;
    private int depth; 
    private String dominoName;
    public Node(String top, String bottom, String dominoName){
        this.top = top;
        this.bottom = bottom;
        this.depth = 0;
        this.dominoName = dominoName;
    }

    public String toString(){
        
        return "Top: " + this.top + " - Bottom: " + this.bottom;
    }
    public boolean isRootNode(){
        if ((this.top.length() == 0) && (this.bottom.length() == 0)){
            return true;
        }
        return false;
    }

    public String getTop(){
        return this.top;
    }

    public String getBottom(){
        return this.bottom;
    }

    public boolean isValidNode(){
        
        if (this.top.startsWith(this.bottom) || this.bottom.startsWith(this.top)){
            return true;
        }
        return false;
    }

    public boolean isSolutionNode(){
        if (this.isRootNode()){
            return false;
        }
        if (top.equals(bottom)){
            return true;
        }
        return false;
    }

    public String getDifference(){
        if (top.length() == 0) {
            return bottom;
        }
        if (bottom.length() == 0) {
            return top;
        }
        int diff = Math.abs(top.length() - bottom.length());

        if (top.length() > bottom.length()){
            return "+"+top.substring(top.length() - diff);
        }
        else{
            return "-"+bottom.substring(bottom.length() - diff);
        }
    }

    public String getDominoName(){
        return dominoName;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public int getSize(){
        String[] paths = dominoName.split(",");
        return paths.length;
    }
}
