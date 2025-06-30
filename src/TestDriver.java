import th.ac.npru.se.ntk.product.ProductSingleton;

public class TestDriver {

	public static void main(String[] args) {
		ProductSingleton p1 = ProductSingleton.getInstance();
		//p1.setProduct("P004", "Switch2", 19800);
		//p1.showProducut();
//		p1.insertProduct("P004", "Switch2", 19800);
		String p_name = p1.getProductName("P004");
		System.out.println("Product Name:" + p_name);
	}
}
