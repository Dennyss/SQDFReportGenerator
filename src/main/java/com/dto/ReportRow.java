package com.dto;

/**
 * Created by Denys Kovalenko on 12/30/2014.
 */
public class ReportRow {
    private String vin;
    private String correlationId;
    private String createTimestamp;
    private String serviceType;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportRow reportRow = (ReportRow) o;

        if (correlationId != null ? !correlationId.equals(reportRow.correlationId) : reportRow.correlationId != null)
            return false;
        if (createTimestamp != null ? !createTimestamp.equals(reportRow.createTimestamp) : reportRow.createTimestamp != null)
            return false;
        if (serviceType != null ? !serviceType.equals(reportRow.serviceType) : reportRow.serviceType != null)
            return false;
        if (vin != null ? !vin.equals(reportRow.vin) : reportRow.vin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vin != null ? vin.hashCode() : 0;
        result = 31 * result + (correlationId != null ? correlationId.hashCode() : 0);
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        return result;
    }
}
