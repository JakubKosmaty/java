public class Main {
    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();

        Thread thread_1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                shop.deliveryOfGoods("Mleko", 1);
                shop.deliveryOfGoods("Jajka", 1);
            }
        });

        Thread thread_2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                shop.purchase("Jajka", 1, ShopInterface.Customer.PATIENT);
            }
        });

        Thread thread_3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                shop.purchase("Mleko", 1, ShopInterface.Customer.PATIENT);
            }
        });

        Thread thread_4 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                shop.deliveryOfGoods("Mleko", 1);
                shop.deliveryOfGoods("Jajka", 1);
            }
        });

        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();

        thread_2.join();
        thread_1.join();
        thread_4.join();
        thread_3.join();

        System.out.println("--------------------------------");
        shop.stockStatus().forEach((key, value) -> System.out.println(key + " " + value));
    }
}
