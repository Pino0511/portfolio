namespace Esame_OrderAPI.Models.Configurations;

public class FileConfiguration
{
    public string UsersFile { get; set; }= "Data/UserFile.json";
    public string CustomersFile { get; set; } = "Data/CustomersFile.json";
    public string OrdersFile { get; set; } = "Data/OrdersFile.json";
}