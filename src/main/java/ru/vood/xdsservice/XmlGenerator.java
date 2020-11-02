package ru.vood.xdsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.vood.plugin.generated.from.xsd.CustomerType;
import ru.vood.plugin.generated.from.xsd.OrderType;
import ru.vood.plugin.generated.from.xsd.Root;
import ru.vood.plugin.generated.from.xsd.ShipInfoType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@Service
public class XmlGenerator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Root root = new Root();

        Root.Customers customers = new Root.Customers();
        List<CustomerType> customerList = customers.getCustomer();

        IntStream.range(0, 1000)
                .parallel()
                .forEach(i -> {
                    CustomerType ct = new CustomerType();
                    ct.setCompanyName(multiply("setCompanyName", i));
                    ct.setContactName(multiply("setContactName", i));
                    ct.setContactTitle(multiply("setContactTitle", i));
                    ct.setFax(multiply("setFax", i));
                    ct.setCustomerID(multiply("setCustomerID", i));
                    customerList.add(ct);
                });

        Root.Orders orders = new Root.Orders();
        List<OrderType> orderList = orders.getOrder();
        IntStream.range(0, 1000)
                .parallel()
                .forEach(i -> {
                    OrderType ot = new OrderType();
                    ot.setCustomerID(multiply("setCustomerID", i));
                    ot.setEmployeeID(multiply("setCustomerID", i));
//                    ot.setOrderDate(new XMLGregorianCalendarImpl());
                    ShipInfoType shipInfoType = new ShipInfoType();
                    shipInfoType.setFreight(new BigDecimal(i));
                    shipInfoType.setShipAddress(multiply("setShipAddress", i));
                    shipInfoType.setShipCity(multiply("setShipCity" , i));
                    shipInfoType.setShipName(multiply("setShipName" , i));
                    ot.setShipInfo(shipInfoType);
                    orderList.add(ot);
                });


        root.setCustomers(customers);

        root.setOrders(orders);

        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
//        jaxbMarshaller.marshal(root, System.out);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(root, new File("employees.xml"));

    }

    private String multiply(String setCustomerID, int i) {
        return IntStream.range(0, i*40)
                .parallel()
                .mapToObj(i1 -> i+setCustomerID)
                .reduce((o1, o2) -> o1 + o2)
                .orElse("kdjshakjdshf");

    }
}
