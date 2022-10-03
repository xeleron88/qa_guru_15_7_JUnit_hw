package guru.xel;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import guru.xel.data.Brands;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static guru.xel.data.Brands.*;

public class WebTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }


    @ValueSource(strings = {"Git Pocket Guide", "Learning JavaScript Design"})
    @ParameterizedTest(name = "Check {0} book in store")
    void demoqaBookTest(String testData) {
        open("https://demoqa.com/books");
        $("#searchBox").setValue(testData);
        $$(".rt-tr-group")
                .first()
                .shouldHave(text(testData));
    }

    @CsvSource(delimiter = '|', value = {
            "Git Pocket Guide | This pocket guide is the perfect on-the-job companion to Git",
            "Learning JavaScript Design | With Learning JavaScript Design Patterns"
    })
    @ParameterizedTest(name = "Book description availability check for {0}")
    void BookDescriptionAvailabilityCheckText(String searchQuery, String expectedText) {
        open("https://demoqa.com/books");
        $("#searchBox").setValue(searchQuery);
        $$("a").findBy(text(searchQuery)).click();
        $("#description-wrapper").shouldHave(text(expectedText));
    }

    static Stream<Arguments> checkingForBrandsInMaxFishing() {
        return Stream.of(
                Arguments.of(List.of("13 Fishing", "Abu Garcia", "Aiko", "Aquatic", "BFT"), UDILISCHA),
                Arguments.of(List.of("Abu Garcia", "Adusta", "Aiko", "Anglers System", "Bait Breath"), PRIMANKI)
        );

    }
    @MethodSource("checkingForBrandsInMaxFishing")
    @ParameterizedTest(name = "Checking first 5 brands {0} in a category {1}")
    void selenideSiteButtonsText(List<String> brands, Brands locale) {
        open("https://maxfishing.ru/catalog/");
        $$("a").find(text(locale.getDesc())).click();
        $$(".custom-control").first(5).shouldHave(CollectionCondition.texts(brands));

    }
}

