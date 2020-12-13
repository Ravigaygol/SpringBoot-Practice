package com.studentMain.ModelData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private List<Student>slist=new ArrayList<>(Arrays.asList(new Student(1, "AAA", "Java"),
	new Student(2, "BBB", "C"),new Student(3, "CCC", "C++"),new Student(4, "DDD", "Python")));
	public List<Student>getAllStudent()
	{
		return slist;
	}
	
	public Student addStudent(Student s)
	{
		slist.add(s);
		return s;
	}
	public Student getStudentById(int sid)
	{
		Student s=null;
		for(Student st:slist)
		{
			if(st.getSid()==sid)
			{
				s=st;
				break;
			}
		}
		return s;
	}
	public Student getStudentByName(String name)
	{
		Student s1=null;
		for(Student st1:slist)
		{
			if(st1.getSname().equalsIgnoreCase(name))
			{
				s1=st1;
				break;
			}
		}
		return s1;
	}
	public Student getStudentByCourse(String course)
	{
		Student s2=null;
		for(Student st2:slist)
		{
			if(st2.getCourse().equalsIgnoreCase(course))
			{
				s2=st2;
				break;
			}
		}
		return s2;
	}
	public void updateStudentById(Student ss,int sid)
	{
		int i=0;
		for(Student s:slist)
		{
			if(s.getSid()==sid)
			{
				slist.set(i, ss);
				break;
			}
			else
				i++;
		}
	}
	public void deleteStudentById(int sid)
	{
		Iterator<Student>it=slist.iterator();
		while(it.hasNext())
		{
			if(it.next().getSid()==sid)
			{
				it.remove();
				break;
			}
		}
	}
	
}
