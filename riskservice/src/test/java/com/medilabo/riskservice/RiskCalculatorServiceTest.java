package com.medilabo.riskservice;

import com.medilabo.riskservice.service.RiskCalculatorService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RiskCalculatorServiceTest {
    //Unit test for assessRisk method

    @Test
    public void testAssessRiskNone() {
        assertEquals(RiskCalculatorService.RiskLevel.NONE, RiskCalculatorService.assessRisk(25, "M", 1));
        assertEquals(RiskCalculatorService.RiskLevel.NONE, RiskCalculatorService.assessRisk(25, "F", 1));
    }

    @Test
    public void testAssessRiskMale() {
        assertEquals(RiskCalculatorService.RiskLevel.BORDERLINE, RiskCalculatorService.assessRisk(35, "M", 3));
        assertEquals(RiskCalculatorService.RiskLevel.IN_DANGER, RiskCalculatorService.assessRisk(35, "M", 6));
        assertEquals(RiskCalculatorService.RiskLevel.EARLY_ONSET, RiskCalculatorService.assessRisk(35, "M", 8));
        assertEquals(RiskCalculatorService.RiskLevel.IN_DANGER, RiskCalculatorService.assessRisk(25, "M", 4));
        assertEquals(RiskCalculatorService.RiskLevel.EARLY_ONSET, RiskCalculatorService.assessRisk(25, "M", 5));
    }

    @Test
    public void testAssessRiskFemale() {
        assertEquals(RiskCalculatorService.RiskLevel.BORDERLINE, RiskCalculatorService.assessRisk(35, "F", 3));
        assertEquals(RiskCalculatorService.RiskLevel.IN_DANGER, RiskCalculatorService.assessRisk(35, "F", 6));
        assertEquals(RiskCalculatorService.RiskLevel.EARLY_ONSET, RiskCalculatorService.assessRisk(35, "F", 8));
        assertEquals(RiskCalculatorService.RiskLevel.IN_DANGER, RiskCalculatorService.assessRisk(25, "F", 5));
        assertEquals(RiskCalculatorService.RiskLevel.EARLY_ONSET, RiskCalculatorService.assessRisk(25, "F", 7));
    }

    @Test
    public void testAssessRiskInvalidGender() {
        assertEquals(RiskCalculatorService.RiskLevel.NONE, RiskCalculatorService.assessRisk(25, "X", 5));
    }

    @Test
    public void testCalculateAge() {
        assertEquals(27, RiskCalculatorService.calculateAge("1998-01-01"));
        assertEquals(32, RiskCalculatorService.calculateAge("1993-01-01"));
    }
}