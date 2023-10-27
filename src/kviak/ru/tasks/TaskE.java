package kviak.ru.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Exchange {
    private List<Order> buyOrders;
    private List<Order> sellOrders;
    private List<Operation> operations;

    public int getOrderId() {
        return orderId;
    }

    private int orderId;

    public Exchange() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        operations = new ArrayList<>();
        orderId = 1;
    }

    public void addOrder(String orderType, double price, int volume) {
        Order order = new Order(orderId, orderType, price, volume);
        orderId++;
        if (orderType.equals("BUY")) {
            buyOrders.add(order);
            buyOrders.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
        } else {
            sellOrders.add(order);
            sellOrders.sort(Comparator.comparing(Order::getPrice));
        }
        matchOrders();
    }

    public void matchOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty() && buyOrders.get(0).getPrice() >= sellOrders.get(0).getPrice()) {
            Order buyOrder = buyOrders.get(0);
            Order sellOrder = sellOrders.get(0);
            int minVolume = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
            operations.add(new Operation(buyOrder.getId(), sellOrder.getId(), sellOrder.getPrice(), minVolume));
            buyOrder.setVolume(buyOrder.getVolume() - minVolume);
            sellOrder.setVolume(sellOrder.getVolume() - minVolume);
            if (buyOrder.getVolume() == 0) {
                buyOrders.remove(0);
            }
            if (sellOrder.getVolume() == 0) {
                sellOrders.remove(0);
            }
        }
    }

    public List<String> getOrders() {
        List<String> orders = new ArrayList<>();
        for (Order order : sellOrders) {
            orders.add(order.toString());
        }
        for (Order order : buyOrders) {
            orders.add(order.toString());
        }
        return orders;
    }

    public String deleteOrder(int orderId) {
        for (int i = 0; i < buyOrders.size(); i++) {
            if (buyOrders.get(i).getId() == orderId) {
                buyOrders.remove(i);
                return "DELETED";
            }
        }
        for (int i = 0; i < sellOrders.size(); i++) {
            if (sellOrders.get(i).getId() == orderId) {
                sellOrders.remove(i);
                return "DELETED";
            }
        }
        return "NOT FOUND";
    }

    public List<String> showOperations(int amount) {
        List<String> operationStrings = new ArrayList<>();
        int startIndex = Math.max(operations.size() - amount, 0);
        List<Operation> recentOperations = operations.subList(startIndex, operations.size());
        for (Operation operation : recentOperations) {
            operationStrings.add(operation.toString());
        }
        return operationStrings;
    }
}

class Order {
    private int id;
    private String orderType;
    private double price;
    private int volume;

    public Order(int id, String orderType, double price, int volume) {
        this.id = id;
        this.orderType = orderType;
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + " " + orderType + " " + price + " " + volume;
    }
}

class Operation {
    private int buyOrderId;
    private int sellOrderId;
    private double price;
    private int volume;

    public Operation(int buyOrderId, int sellOrderId, double price, int volume) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return buyOrderId + " " + sellOrderId + " " + price + " " + volume;
    }
}

public class TaskE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exchange exchange = new Exchange();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("ADD")) {
                String orderType = command[1];
                double price = Double.parseDouble(command[2]);
                int volume = Integer.parseInt(command[3]);
                exchange.addOrder(orderType, price, volume);
                System.out.println(exchange.getOrderId() - 1);
            } else if (command[0].equals("GET")) {
                List<String> orders = exchange.getOrders();
                orders.forEach(System.out::println);
            } else if (command[0].equals("DELETE")) {
                int orderId = Integer.parseInt(command[1]);
                System.out.println(exchange.deleteOrder(orderId));
            } else if (command[0].equals("SHOW_OPERATIONS")) {
                int amount = Integer.parseInt(command[1]);
                List<String> operations = exchange.showOperations(amount);
                operations.forEach(System.out::println);
            }
        }
    }


}


