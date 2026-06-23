public class License
{
    private static int counter = 1;
    private Car licenseHolder;
    private int id;
    private int validGarageId;

    public License(Car licenseHolder, int validInGarageId)
    {
        this.licenseHolder = licenseHolder;
        this.validGarageId = validInGarageId;
        this.id = counter;
        counter++;
    }

    public String getLicenseHolderPlate()
    {
        return licenseHolder.getLicensePlate();
    }

    public int getValidInGarageId()
    {
        return validGarageId;
    }
}
