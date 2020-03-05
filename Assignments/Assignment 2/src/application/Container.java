package application;
/**
 * Simulates a container of donation packages. Uses a stack data structure
 * @author Marcus Brooks
 *
 */
public class Container implements ContainerInterface {
	private MyStack<DonationPackage> stack;
	/**
	 * Default constructor that uses the default capacity
	 */
	public Container() {
		stack = new MyStack<DonationPackage>();
	}
	/**
	 * Parameterized constructor that creates a custom sized container
	 * @param size size of the container
	 */
	public Container(int size) {
		stack = new MyStack<DonationPackage>(size);
	}
	/**
	 * loads the container with a donation package
	 * @param dPackage the donation package
	 * @return a boolean value representing whether the package was added or not
	 * @throws ContainerException thrown if the container is full
	 */
	public boolean loadContainer(DonationPackage dPackage) throws ContainerException {
		if (stack.isFull()) {
			throw new ContainerException("Container Stack is full");
		}
		return stack.push(dPackage);
	}
	/**
	 * Removes a package from the container
	 * @return returns the donation package that was removed
	 * @throws ContainerException thrown if the container is empty
	 */
	public DonationPackage removePackageFromContainer() throws ContainerException {
		if (stack.isEmpty()) {
			throw new ContainerException("Container Stack is empty");
		}
		return stack.pop();
	}
	/**
	 * Returns an array representation of the container
	 * @return an array representation of the container
	 */
	public DonationPackage[] toArrayPackage() {
		Object[] test = stack.toArray();
		DonationPackage[] test2 =  new DonationPackage[stack.size()];
		for (int i = 0; i < test2.length; i++) {
			test2[i] = (DonationPackage) test[i];
		}
		return test2;
	}
}