package EmployeeAssect.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import  EmployeeAssect.hibernate.App ;

import EmployeeAssect.hibernate.Assets;



public class App 
{
    public static void main( String[] args )
    {
        
  try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Project Started....");
	    	
	    	
	    	System.out.println("Enter 1 for addDetails");
	    	System.out.println("Enter 2 for get the details");
	    	System.out.println("Enter 3 for update the details ");
	    	System.out.println("Enter 4 for delete particular Movie Id details");
	    	System.out.println("Enter the no.: ");
	    	
	    	int num = sc.nextInt();
	    	
	    	switch(num){
	    	case 1:
	    		EmployeeDetails E1 = new EmployeeDetails();
			
		    	System.out.print("Enter the AssetId: ");
		    	Integer AssetId = Integer.parseInt(sc.nextLine());
		    	System.out.print("Enter Employee Name: ");
		    	String EmpName = sc.nextLine();
		    	System.out.print("Enter the Password: ");
		    	String Password = sc.nextLine();
		    	System.out.println("Enter the AssetsType: ");
		    	String AssetsType = sc.nextLine();
		    	System.out.println("Enter the Assets Location: ");
		    	String AssetsLocation = sc.nextLine();
		    	System.out.println("Enter the Assets price");
		    	Integer AssetsPrice = sc.nextInt();
		    	
				E1.setAssetId(AssetId);
				E1.setEmpId(100+AssetId);
				E1.setEmpName(EmpName);
				E1.setPassword(Password);
				Assets a1 = new Assets();
				a1.setAssetsId(AssetId);
				a1.setSerialNo(200+a1.getAssetsId());
				a1.setPrintNo(10+AssetId);
				a1.setAssetsType(AssetsType); 
				a1.setAssetsLocation(AssetsLocation);
				a1.setAssetsPrice(AssetsPrice);
				add(E1 , a1);
				System.out.println("Details added");
				
	    	case 2:
	    		System.out.println("Enter 1 for getting the Employee details");
		    	System.out.println("Enter 2 for getting the Assets details with maximum price");
		    	System.out.println("Enter 3 for getting total no. of employees ");
		    	System.out.println("Enter 4 for getting the Employee details in ascending order by name ");
		    	System.out.println("Enter 5 for Employee details and Assets details");
		    	System.out.println("Enter the no.: ");
		    	Integer value = sc.nextInt();
		    	switch(value) {
		    	case 1:
		    		System.out.println("Enter the Employee Id : ");
		    		Integer getId = sc.nextInt();
		    		System.out.println(getDetails(getId));
		    		return;
		    	case 2:
		    		System.out.println(Max());
		    		return;
		    	case 3:
		    		System.out.println(CountEmployee());
		    		return;
		    	case 4:
		    		Iterator itr = EmployeeDetails().iterator();
					while(itr.hasNext()) {
							System.out.println(itr.next());
						}	
					return;
		    	case 5:
		    		System.out.print("Enter the Employee id:");
		    		Integer empId = sc.nextInt();
		    		EmployeeIdDetails(empId);
		    		return;
		    	default:
		    		System.out.println("Enter the valid Number!");
		    		return;
		    	}
	    	case 3:
	    		System.out.print("Enter the Employee id:");
	    		Integer empId2 = sc.nextInt();
	    		updatePassword(empId2);
	    		return;
	    	case 4:
	    		System.out.print("Enter the Employee id:");
	    		Integer empId3 = sc.nextInt();
	    		deleteEmployee(empId3);
	    		return;
	    	default:
	    		System.out.println("Enter the valid number.");
	    		return;
	    	
	    	}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public static void add(EmployeeDetails EmpDetails, Assets AssetDetails) {
				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");
				SessionFactory factory = cfg.buildSessionFactory();
				Session session = factory.openSession();
				session.beginTransaction();
				session.save(EmpDetails);
				session.save(AssetDetails);
				session.getTransaction().commit();
	   
		        session.close();
				System.out.println(EmpDetails);
				System.out.println(AssetDetails);
				return;
			}
    public static EmployeeDetails getDetails(Integer EmpId) {
    	try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			
			session.beginTransaction();
			EmployeeDetails emp = (EmployeeDetails)session.get(EmployeeDetails.class , EmpId);
			
			session.getTransaction().commit();
	
	        session.close();
			return emp;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("No Details found");
    	}
    	return new EmployeeDetails();
    	
	}
    public static Assets Max() {
    	
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			
			session.beginTransaction();
//			SQLQuery<Assest> query = session.createSQLQuery("Select a. assestId ,a.assestLocation,a.assestPrice,a.assestType, a.printNo, a.serialNo from assest a where a.assestPrice = (Select max(assestPrice) from assest); ");
			
			Query<Assets> query = session.createQuery("FROM Assets where assetsPrice = (Select max(assetsPrice) from Assets)");
			
			List<Assets> list = query.list();
//			EmployeeDetails emp = (EmployeeDetails)session.get(EmployeeDetails.class , EmpId);
			
			session.getTransaction().commit();
	
	        session.close();
			
    	
    	return list.get(0);
    	
	}
    public static List<Integer> CountEmployee() {
    	
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
//		SQLQuery<Assest> query = session.createSQLQuery("Select a. assestId ,a.assestLocation,a.assestPrice,a.assestType, a.printNo, a.serialNo from assest a where a.assestPrice = (Select max(assestPrice) from assest); ");
		
		Query<Integer> query = session.createQuery("Select count(*) FROM EmployeeDetails");
		
		List<Integer> list = query.list();
//		EmployeeDetails emp = (EmployeeDetails)session.get(EmployeeDetails.class , EmpId);
		
		session.getTransaction().commit();

        session.close();
		
	
	return list;
	
}
public static List<EmployeeDetails> EmployeeDetails() {
    	
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
//		SQLQuery<Assest> query = session.createSQLQuery("Select a. assestId ,a.assestLocation,a.assestPrice,a.assestType, a.printNo, a.serialNo from assest a where a.assestPrice = (Select max(assestPrice) from assest); ");
		
		Query<EmployeeDetails> query = session.createQuery(" FROM EmployeeDetails order by empName ASC");
		
		List<EmployeeDetails> list = query.list();
//		EmployeeDetails emp = (EmployeeDetails)session.get(EmployeeDetails.class , EmpId);
		
		session.getTransaction().commit();

        session.close();
		
	
	return list;
	
}
	public static void EmployeeIdDetails(Integer EmpId) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
	//	SQLQuery<Assest> query = session.createSQLQuery("Select a. assestId ,a.assestLocation,a.assestPrice,a.assestType, a.printNo, a.serialNo from assest a where a.assestPrice = (Select max(assestPrice) from assest); ");
		
		Query<Assets> query = session.createQuery(" FROM Assets where assetId = (select assetId from EmployeeDetails where empId= : empId) ");
		query.setParameter("empId", EmpId);
		
		Query<EmployeeDetails> query1 = session.createQuery(" FROM EmployeeDetails where empId = :empId ");
		query1.setParameter("empId", EmpId);
		
		List<Assets> list = query.list();
		List<EmployeeDetails> list1 = query1.list();
		System.out.println(list1.get(0));
		System.out.println(list.get(0));
	//	EmployeeDetails emp = (EmployeeDetails)session.get(EmployeeDetails.class , EmpId);
		
		session.getTransaction().commit();
	
	    session.close();
	
	}
	public static void updatePassword(Integer EmpId) {
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
	       
	      
	        Session session = factory.openSession();
			
	        session.beginTransaction();
			EmployeeDetails m1 = session.load(EmployeeDetails.class, EmpId);
			System.out.print("Enter the new password:");
			Scanner sc = new Scanner(System.in);
			String newpassword = sc.nextLine();
			m1.setPassword(newpassword);
			System.out.println("Password updated!");
			session.update(m1);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	public static void deleteEmployee(Integer EmpId) {
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
	       
	      
	        Session session = factory.openSession();
			
	        session.beginTransaction();
	        Query<Integer> query = session.createQuery("select assetId from EmployeeDetails where empId= : empId ");
			query.setParameter("empId", EmpId);
			List<Integer> list = query.list();
			Assets a1 = session.load(Assets.class, list.get(0));
			
	        
	        EmployeeDetails m1 = session.load(EmployeeDetails.class, EmpId);
			session.delete(m1);
			session.getTransaction().commit();
			session.close();
			System.out.println("deleted!");
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
    
    
}
