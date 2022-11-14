package Test;

import Base.BaseTest;
import Base.Screenshot;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUserChecksOpenPosition extends BaseTest {

    @Rule
    public Screenshot rule = new Screenshot(driver, "screenshots/");


    @Test
    public void testUserChecksQualityAssurancePositions() throws InterruptedException {
        assertEquals(homePage.getCurrentUrl(), BASE_URL);
        assertEquals(homePage.getPageTitle(), "#1 Leader in Individualized, Cross-Channel CX — Insider");
        homePage.clickMoreMenuButton();
        careersPage = homePage.goToCareersPage();
        assertEquals(careersPage.getCurrentUrl(),BASE_URL+"careers/");
        assertEquals(careersPage.getPageTitle(), "Insider Careers");
        assertTrue(careersPage.getTeamsSection().isDisplayed() &&
                careersPage.getLifeAtInsiderSection().isDisplayed() &&
                careersPage.getLocationSection().isDisplayed());
        assertEquals(careersPage.getLocationItemsCount(), 29);
        careersPage.clickLoadMoreButton();
        teamPage = careersPage.clickTeamButton("Quality Assurance");
        openPositionPage = teamPage.goToOpenPositionPage();
        openPositionPage.selectLocationFilter("Istanbul");
        openPositionPage.selectDepartmentFilter("Quality Assurance");
        assertTrue(openPositionPage.checkOpenPositionItem("Quality Assurance", "Istanbul,Turkey", "Quality Assurance" ));
        assertTrue(openPositionPage.clickToApplyNowButton());

    }



}
