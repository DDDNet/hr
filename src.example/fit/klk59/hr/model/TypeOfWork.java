package klk59.hr.model;

import domainapp.basics.exceptions.ConstraintViolationException;
import domainapp.basics.model.meta.AttrRef;
import domainapp.basics.model.meta.DAttr;
import domainapp.basics.model.meta.DAttr.Type;
import domainapp.basics.model.meta.DClass;
import domainapp.basics.model.meta.DOpt;
import domainapp.basics.util.Tuple;

@DClass(schema="hr")
public class TypeOfWork {
	// attributes
	@DAttr(name="id",id=true,auto=true,type=Type.Integer,length=3,mutable=false,optional=false)
	private int id;
	private static int idCounter;
	
	@DAttr(name="name",type=Type.String,length=30,optional=false)
	private String name;
	
	// constructor method: create objects from data source
	@DOpt(type=DOpt.Type.ObjectFormConstructor)
	@DOpt(type=DOpt.Type.RequiredConstructor)
	public TypeOfWork(@AttrRef("name") String name) {
	    this(null, name);
	}
	
	@DOpt(type=DOpt.Type.DataSourceConstructor)
	public TypeOfWork(Integer id, String name)
			throws ConstraintViolationException {
	    this.id = nextID(id);
		this.name = name;
	    
	}
	
	private static int nextID(Integer currID) {
	    if (currID == null) {
	      idCounter++;
	      return idCounter;
	    } else {
	      int num = currID.intValue();
	      if (num > idCounter)
	        idCounter = num;
	      
	      return currID;
	    }
	}
	
	public int getId() {
	    return id;
	}
	  
	
	public void setName(String name) {
	    this.name = name;
	}
	
	// getter methods
	public String getName() {
	    return name;
	}
	
	// override toString
	  @Override
	  public String toString() {
	    return this.getClass().getSimpleName() + "("  + getName()
	        + ")";
	  }

	 
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    return true;
	  }
	  
	  /**
	   * @requires 
	   *  minVal != null /\ maxVal != null
	   * @effects 
	   *  update the auto-generated value of attribute <tt>attrib</tt>, specified for <tt>derivingValue</tt>, using <tt>minVal, maxVal</tt>
	   */
	  @DOpt(type=DOpt.Type.AutoAttributeValueSynchroniser)
	  public static void updateAutoGeneratedValue(
	      DAttr attrib,
	      Tuple derivingValue, 
	      Object minVal, 
	      Object maxVal) throws ConstraintViolationException {
	    
	    if (minVal != null && maxVal != null) {
	      //TODO: update this for the correct attribute if there are more than one auto attributes of this class 
	      int maxIdVal = (Integer) maxVal;
	      if (maxIdVal > idCounter)  
	        idCounter = maxIdVal;
	    }
	  }
}
