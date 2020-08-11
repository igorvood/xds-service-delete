package ru.vood.xdsservice;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.vood.plugin.generated.from.xsd.CustomerType;
import ru.vood.plugin.generated.from.xsd.OrderType;
import ru.vood.plugin.generated.from.xsd.Root;
import ru.vood.plugin.generated.from.xsd.ShipInfoType;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

@Service
public class XmlGenerator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Root root = new Root();

        Root.Customers customers = new Root.Customers();
        List<CustomerType> customerList = customers.getCustomer();

        IntStream.range(0, 1000)
                .forEach(i -> {
                    CustomerType ct = new CustomerType();
                    ct.setCompanyName("setCompanyName" + i);
                    ct.setContactName("setContactName" + i);
                    ct.setContactTitle("setContactTitle" + i);
                    ct.setFax("setFax" + i);
                    ct.setCustomerID("setCustomerID" + i);
                    customerList.add(ct);
                });

        Root.Orders orders = new Root.Orders();
        List<OrderType> orderList = orders.getOrder();
        IntStream.range(0, 1000)
                .forEach(i -> {
                    OrderType ot = new OrderType();
                    ot.setCustomerID("setCustomerID" + i);
                    ot.setEmployeeID("setCustomerID" + i);
                    ot.setOrderDate(new XMLGregorianCalendarImpl());
                    ShipInfoType shipInfoType = new ShipInfoType();
                    shipInfoType.setFreight(new BigDecimal(i));
                    shipInfoType.setShipAddress("setShipAddress" + i);
                    shipInfoType.setShipCity("setShipCity" + i);
                    shipInfoType.setShipName("setShipName" + i);
                    ot.setShipInfo(shipInfoType);
                    orderList.add(ot);
                });


        root.setCustomers(customers);

        root.setOrders(orders);
    }
}
