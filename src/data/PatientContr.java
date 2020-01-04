package data;

import exceptions.NotAValidValue;

import java.math.BigDecimal;

final public class PatientContr {

    private final BigDecimal patientContr;

    public PatientContr(BigDecimal code) throws NotAValidValue {
        if (code == null) {
            throw new NullPointerException("A value class must not be null.");
        }
        if (!(code.compareTo(BigDecimal.ZERO) >= 0 && code.compareTo(BigDecimal.valueOf(100)) <= 0)) {
            throw new NotAValidValue("Code is not a patient contribution.");
        }
        this.patientContr = code;
    }

    public BigDecimal getPatientContr() {
        return patientContr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        data.PatientContr prodID = (data.PatientContr) o;
        return patientContr.equals(prodID.patientContr);
    }

    @Override
    public int hashCode() {
        return patientContr.hashCode();
    }

    @Override
    public String toString() {
        return "PatientContr{" + "patient contribution='" + patientContr + '\'' + '}';
    }
}
