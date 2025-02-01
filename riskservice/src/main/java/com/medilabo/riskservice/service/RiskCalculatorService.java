package com.medilabo.riskservice.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class RiskCalculatorService {
    //A simple rule engine that calculates the risk level of a patient based on their risk factors and age
        public enum RiskLevel {
            NONE, BORDERLINE, IN_DANGER, EARLY_ONSET
        }

        public static Integer calculateAge(String age) {
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(age);
            Period yearsOld = birthDate.until(today);
            return yearsOld.getYears();
        }

        public static RiskLevel assessRisk(Integer age, String gender, Integer riskFactors) {
            if (riskFactors <= 1) {
                return RiskLevel.NONE;
            }

            return switch (gender.toUpperCase()) {
                //https://www.geeksforgeeks.org/switch-statement-in-java/
                case "M" -> assessMaleRisk(age, riskFactors);
                case "F" -> assessFemaleRisk(age, riskFactors);
                default -> RiskLevel.NONE;
            };
        }

        private static RiskLevel assessMaleRisk(Integer age, Integer riskFactors) {
            if (age > 30) {
                return switch (riskFactors) {
                    case 2, 3, 4, 5 -> RiskLevel.BORDERLINE;
                    case 6, 7 -> RiskLevel.IN_DANGER;
                    default -> riskFactors >= 8 ? RiskLevel.EARLY_ONSET : RiskLevel.NONE;
                };
            } else {
                return switch (riskFactors) {
                    case 3, 4 -> RiskLevel.IN_DANGER;
                    default -> riskFactors >= 5 ? RiskLevel.EARLY_ONSET : RiskLevel.NONE;
                };
            }
        }

        private static RiskLevel assessFemaleRisk(int age, Integer riskFactors) {
            if (age > 30) {
                return switch (riskFactors) {
                    case 2, 3, 4, 5 -> RiskLevel.BORDERLINE;
                    case 6, 7 -> RiskLevel.IN_DANGER;
                    default -> riskFactors >= 8 ? RiskLevel.EARLY_ONSET : RiskLevel.NONE;
                };
            } else {
                return switch (riskFactors) {
                    case 4, 5, 6 -> RiskLevel.IN_DANGER;
                    default -> riskFactors >= 7 ? RiskLevel.EARLY_ONSET : RiskLevel.NONE;
                };
            }
        }
}
