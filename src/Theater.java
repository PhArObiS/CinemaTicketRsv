public class Theater {
private String name;
    private String address;
    private String phoneNumber;
    private int capacity;

    public Theater(String name, String address, String phoneNumber, int capacity) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
