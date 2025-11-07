using Microsoft.Extensions.Options;
using Newtonsoft.Json;
using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Configurations;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.DL.Repositories;

public class OrderRepository : IOrderRepository
{
    FileConfiguration _fileConfiguration;

    public OrderRepository(IOptions<FileConfiguration> fileConfiguration)
    {
        _fileConfiguration = fileConfiguration.Value;
    }
    
    static List<Order> Orders = new List<Order>();

    public Order AddOrder(Order order)
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        if (order == null)
        {
            throw new ArgumentNullException(nameof(order), "User non valido!");
        }

        if (order.Total < 0)
        {
            throw new ArgumentOutOfRangeException(nameof(order.Total), "Il totale deve essere negativo, ciao alessandro");
        }

        order.Id = Orders.Any() ? Orders.Max(u => u.Id) +  1 : 1;
        Orders.Add(order);
        SalvaOrderSuFile(_fileConfiguration.OrdersFile);
        return order;
    }

    public bool ExistById(int id)
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        return Orders.Any(u => u.Id == id);
    }

    public bool DeleteOrder(int id)
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        if (ExistById(id)) 
        {
            Orders.Remove(Orders.First(u => u.Id == id));
            SalvaOrderSuFile(_fileConfiguration.OrdersFile);
            return true;
        }
        return false;
    }

    public Order UpdateOrder(Order order, int id)
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        if (order == null || order.Total < 0) 
        {
            throw new ArgumentNullException(nameof(order), "Order non valido!");
        }

        if (ExistById(id))
        {
            Order previusOrder = Orders.FirstOrDefault(u => u.Id == id);
            previusOrder.Total = order.Total;
        }
        SalvaOrderSuFile(_fileConfiguration.OrdersFile);
        return GetOrderById(order.Id);
    }

    public List<Order> GetOrders()
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        return Orders;
    }

    public Order GetOrderById(int orderId)
    {
        CaricaOrdiniDaFile(_fileConfiguration.OrdersFile);
        return Orders.FirstOrDefault(u => u.Id == orderId);
    }
    
    static void CaricaOrdiniDaFile(string percorsoFile)
    {
        if (File.Exists(percorsoFile))
        {
            try
            {
                string json = File.ReadAllText(percorsoFile);
                Orders = JsonConvert.DeserializeObject<List<Order>>(json) ?? new List<Order>();
                Console.WriteLine("Ordini caricati con successo!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Errore durante il caricamento degli ordini: {ex.Message}");
            }
        }
        else
        {
            Console.WriteLine("File ordini non trovato, nessun ordine caricato.");
        }
    }
    
    static void SalvaOrderSuFile(string percorsoFile)
    {
        try
        {
            string json = JsonConvert.SerializeObject(Orders, Formatting.Indented);
            File.WriteAllText(percorsoFile, json);
            Console.WriteLine("Orders salvati con successo!");
            CaricaOrdiniDaFile(percorsoFile);
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Errore durante il salvataggio degli orders: {ex.Message}");
        }
    }
}