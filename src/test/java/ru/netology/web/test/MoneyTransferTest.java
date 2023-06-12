package ru.netology.web.test;

import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferTest {
    DashboardPage dashboardPage;

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        int card1Balance = dashboardPage.getCardBalance(0);
        int card2Balance = dashboardPage.getCardBalance(1);
        if (card1Balance < 10000) {
            dashboardPage.selectCard(0).amountCard(Integer.toString(10000 - card1Balance), DataHelper.getCard2Info());
        } else if (card1Balance > 10000) {
            dashboardPage.selectCard(1).amountCard(Integer.toString(10000 - card2Balance), DataHelper.getCard1Info());
        }
    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards1() {
        int card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        int card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(0).amountCard("5_000", DataHelper.getCard2Info());
        assertEquals(card1BalanceBeforeTransfer + 5000, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer - 5000, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards2() {
        var card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        var card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(1).amountCard("5_000", DataHelper.getCard1Info());
        assertEquals(card1BalanceBeforeTransfer - 5000, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer + 5000, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards3() {
        int card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        int card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(0).amountCard("9_999", DataHelper.getCard2Info());
        assertEquals(card1BalanceBeforeTransfer + 9_999, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer - 9_999, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards4() {
        var card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        var card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(1).amountCard("9_999", DataHelper.getCard1Info());
        assertEquals(card1BalanceBeforeTransfer - 9_999, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer + 9_999, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards5() {
        int card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        int card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(0).amountCard("10_000", DataHelper.getCard2Info());
        assertEquals(card1BalanceBeforeTransfer + 10_000, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer - 10_000, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldSuccessfulTransferMoneyBetweenOwnCards6() {
        var card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        var card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        dashboardPage.selectCard(1).amountCard("10_000", DataHelper.getCard1Info());
        assertEquals(card1BalanceBeforeTransfer - 10_000, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer + 10_000, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldNotSuccessfulTransferMoneyBetweenOwnCards1() {
        var card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        var card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.selectCard(0);
        transferPage.amountCard("10_001", DataHelper.getCard2Info());
        transferPage.appearanceErrorMessage();
        assertEquals(card1BalanceBeforeTransfer, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldNotSuccessfulTransferMoneyBetweenOwnCards2() {
        var card1BalanceBeforeTransfer = dashboardPage.getCardBalance(0);
        var card2BalanceBeforeTransfer = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.selectCard(1);
        transferPage.amountCard("10_001", DataHelper.getCard1Info());
        transferPage.appearanceErrorMessage();
        assertEquals(card1BalanceBeforeTransfer, dashboardPage.getCardBalance(0));
        assertEquals(card2BalanceBeforeTransfer, dashboardPage.getCardBalance(1));
    }

}

