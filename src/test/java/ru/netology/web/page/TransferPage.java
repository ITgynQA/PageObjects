package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement amountButton = $("[data-test-id=action-transfer]");

    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        amountField.shouldBe(visible);
    }

    public void appearanceErrorMessage() {
        errorMessage.shouldBe(visible);
    }

    public DashboardPage amountCard(String amount, DataHelper.CardInfo cardInfo) {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        amountField.setValue(amount);
        fromField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        fromField.setValue(cardInfo.getCardNumber());
        amountButton.click();
        return new DashboardPage();
    }

}


