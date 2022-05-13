/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.parts;

/**
 * A convenience class implementing the Factory design pattern to
 * help creating new hardware components.
 * This class is also a Singleton, mainly to make sure that a
 * unique serial number is generated for each hardare component
 * 
 */
public class HardwarePartFactory {
    public static final String[] TYPES = { "MOTHERBOARD", "RAM", "GPU" };
    private int serial = 0;

    private static final HardwarePartFactory factory = new HardwarePartFactory();

    /**
     * Private constructor: this is neeeded to male this calss a Singleton
     */
    private HardwarePartFactory() {
    }

    /**
     * This is the method that needs to be called to get a HardwareFactory
     * object
     * 
     * @return the Singketon representing the HardwarePart factory
     */
    public static synchronized HardwarePartFactory getFactory() {
        return factory;
    }

    /**
     * This is the method simulating the production of a hardware component.
     * Note that in case the description string does not correspond to any
     * known component, a motherboard is returned by default.
     * Also note that this method will simulate production time by putting the
     * calling thread to sleep for the amount of time specific to the
     * component being created (that is why it may throw an InterruptedException,
     * which must be handled by the calling method).
     * 
     * Note that createHardwarePart() returns a HardwarePart, so you will likely
     * need to cast it
     * to the proper type before using the returned object; e.g.: RAM ram =
     * (RAM)f.createHardwarePart("RAM").
     * This is not needed if you use the object as a HardwarePart; e.g.:
     * HardwarePart ram = f.createHardwarePart("RAM")
     * 
     * @param description a String to specify which part needs to be created (needs
     *                    to be one of the existign parts)
     * @return an object of the required type (note that it needs *casting*)
     * @throws InterruptedException the creation of a part is simulated by having
     *                              the calling thread sleep for
     *                              the approriate amount of time; the exception is
     *                              not handled so it will need to be caught by the
     *                              caller
     */
    public synchronized HardwarePart createHardwarePart(String description) throws InterruptedException {
        int newSerial = (serial++) % 100000;

        switch (description) {
            case "RAM":
                RAM ram = new RAM(newSerial);
                Thread.sleep(ram.getProdutionTime());
                return ram;
            case "GPU":
                GPU gpu = new GPU(newSerial);
                Thread.sleep(gpu.getProdutionTime());
                return gpu;
            case "MOTHERBOARD":
            default:
                MotherBoard mb = new MotherBoard(newSerial);
                Thread.sleep(mb.getProdutionTime());
                return mb;
        }

    }

}
