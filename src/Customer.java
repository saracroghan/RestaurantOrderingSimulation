
public class Customer {
	String name;
	String order;
	Long start_time;
	Long end_time;
	Long elapsed_time;
	
	public Customer(String n,String o) {
		this.name = n;
		this.order = o;
	}
	public void setStartTime(long start) {
		this.start_time = start;
	}
	public String printCustomer() {
		return this.name+"("+this.order+")";
	}
	public String getName() {
		return this.name;
	}
	public String getOrder() {
		return this.order;
	}
	public Long getElapsedTime(long end) {
		this.end_time = end;
		this.elapsed_time =  this.end_time - this.start_time;
		return this.elapsed_time;
				
	}
}
