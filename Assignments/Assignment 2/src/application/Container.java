package application;

public class Container implements ContainerInterface {
	private MyStack<DonationPackage> stack;
	
	public Container() {
		stack = new MyStack<DonationPackage>();
	}
	public Container(int size) {
		stack = new MyStack<DonationPackage>(size);
	}
	public boolean loadContainer(DonationPackage dPackage) throws ContainerException {
		if (stack.isFull()) {
			throw new ContainerException("Container Stack is full");
		}
		return stack.push(dPackage);
	}
	public DonationPackage removePackageFromContainer() throws ContainerException {
		if (stack.isEmpty()) {
			throw new ContainerException("Container Stack is empty");
		}
		return stack.pop();
	}
	public DonationPackage[] toArrayPackage() {
		return stack.toArray();
	}
}