package rtwmas.supervisor.fmm.repository;

import java.util.HashMap;
import java.util.Map;

import rtwmas.supervisor.fmm.model.Data;


public class DataRepository
{
	private Map<String, Data> list = new HashMap<>();

	public boolean addData(String ID, Data data)	{	list.put(ID, data);		return true;}
	
	public Map<String, Data> getMap() 			{	return list;		}
	public void setMap(Map<String, Data> list) 	{	this.list = list;	}
}
