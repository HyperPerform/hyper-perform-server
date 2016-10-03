package me.hyperperform.forecasting.response;

public class UpdateIntegrationResponse
{
    private boolean updated;

    public UpdateIntegrationResponse() {
        this.updated = false;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}
