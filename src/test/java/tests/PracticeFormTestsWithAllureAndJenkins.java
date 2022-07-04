package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTestsWithAllureAndJenkins extends TestBase {

    @Test
    @DisplayName("Some successful registration form test")
    void successfulTest() {
        step("Open registration form", () -> {
            open("/automation-practice-form");

            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill textboxes", () -> {
            $("#firstName").setValue("Artem");
            $("#lastName").setValue("Bobkov");
            $("#userEmail").setValue("abobkov@dnomads.pro");
            $("#userNumber").setValue("1234567899");
            $("#currentAddress").setValue("Moscow");
        });

        step("Fill Subject", () -> {
            $("#subjectsInput").sendKeys("Physics");
            $("#subjectsInput").pressEnter();
        });

        step("Fill date", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("October");
            $(".react-datepicker__year-select").selectOption("1997");
            $(".react-datepicker__day--014").click();
        });

        step("Choose gender radio", () -> $("#genterWrapper").$(byText("Male")).click());

        step("Mark checkboxes", () -> {
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#hobbiesWrapper").$(byText("Music")).click();
        });

        step("Fill state and city", () -> {
            $("#react-select-3-input").setValue("Rajasthan").pressEnter();
            $("#react-select-4-input").setValue("Jaiselmer").pressEnter();
        });

        step("Upload image", () -> {
            $("#uploadPicture").uploadFromClasspath("example.png");
        });

        step("Submit", () -> $("#submit").click());

        step("Check form results", () -> {
            $$(".table-responsive tr").filterBy(text("Student Name")).shouldHave(texts(
                    "Artem Bobkov"));
            $$(".table-responsive tr").filterBy(text("Student Email")).shouldHave(texts(
                    "abobkov@dnomads.pro"));
            $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts(
                    "Male"));
            $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(
                    "1234567899"));
            $$(".table-responsive tr").filterBy(text("Date of Birth")).shouldHave(texts(
                    "14 October,1997"));
            $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(
                    "Physics"));
            $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts(
                    "Sports, Reading, Music"));
            $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts(
                    "example.png"));
            $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(
                    "Moscow"));
            $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(
                    "Rajasthan Jaiselmer"));
        });
    }
}