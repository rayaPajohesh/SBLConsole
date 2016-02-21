package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Title:           EntityList
 * Description:     This class provide encapsulation over java.util.Vector
 * Copyright:       Copyright (c) 2002
 * Company:         Avanza Solutions
 * @author          SADIQ PANJWANI
 * @version         1.0
 */

public class EntityList{

    private Vector list;
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 	
    public EntityList(int size){
        //list = new Vector();
        //list.setSize(size);
    }

    public EntityList(){
        list = new Vector();

    }

    public Object getEntityAt(int index){
        return list.elementAt(index);
    }

     public int count(){
        return this.list.size();
     }

     public void addEntity(Object object){
        this.list.add(object);
     }

     public void removeEntityAt(int index){
        this.list.removeElementAt(index);
     }

     public void removeEntity(Object object){
        this.list.removeElement(object);
     }
}