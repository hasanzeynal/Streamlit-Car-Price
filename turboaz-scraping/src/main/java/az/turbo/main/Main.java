package az.turbo.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String baseSelector = ".products-i";
        String carSelector = ".products-i__name";
        String priceSelector = ".products-i__price .product-price";
        String detailsSelector = ".products-i__attributes";

        List<Car> cars = new ArrayList<>();

        String pageUrl = "https://turbo.az/autos?page=";
        int minPage = 1;
        int maxPage = 2;

        for (int i = minPage; i <= maxPage; i++) {
            WebPage page = new WebPage(pageUrl + i);
            page.connect();

            List<WebElement> webElements = page.fetchWebElements(baseSelector);

            for (WebElement webElement : webElements) {
                String brand = webElement.findElement(By.cssSelector(carSelector)).getText();
                String price = webElement.findElement(By.cssSelector(priceSelector)).getText();
                String details = webElement.findElement(By.cssSelector(detailsSelector)).getText();

                cars.add(new Car(brand, price, details));
            }
        }

        // convert data into format that csv can write
        List<String[]> data = cars.stream().map(
                        car -> new String[]{car.getBrand(), car.getPrice(), car.getDetails()}
                )
                .collect(Collectors.toList());

        // add csv headers
        data.add(0, new String[]{"brand", "price", "details"});

        // export data
        CsvExporter exporter = new CsvExporter();
        boolean isExported = exporter.export(data);
        if (isExported) {
            System.out.println("Csv file created!");
        }
    }
}
