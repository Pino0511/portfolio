using Microsoft.Extensions.Options;
using Newtonsoft.Json;
using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Configurations;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.DL.Repositories;

public class CustomerRepository : ICustomerRepository
{
    FileConfiguration _fileConfiguration;

    public CustomerRepository(IOptions<FileConfiguration> fileConfiguration)
    {
        _fileConfiguration = fileConfiguration.Value;
    }
    
    static List<Customer> Customers = new List<Customer>();
    
    public Customer AddCustomer(Customer customer)
    {
        if (customer == null)
        {
            throw new ArgumentNullException(nameof(customer), "Customer non valido");
        }

        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        customer.Id = Customers.Any() ? Customers.Max(u => u.Id) +  1 : 1;
        Customers.Add(customer);
        SalvaCustomerSuFile(_fileConfiguration.CustomersFile);
        return customer;
    }

    public bool ExistById(int id)
    {
        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        return Customers.Any(c => c.Id == id);
    }

    public bool DeleteCustomer(int id)
    {
        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        if (ExistById(id))
        {
            Customers.Remove(Customers.FirstOrDefault(c => c.Id == id));
            SalvaCustomerSuFile(_fileConfiguration.CustomersFile);
            return true;
        }
        return false;
    }

    public Customer UpdateCustomer(Customer customer, int id)
    {
        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        if (customer == null)
        {
            throw new ArgumentNullException(nameof(customer), "Nuovo Customer non valido");
        }
        if (ExistById(id))
        {
            Customer vecchioC = Customers.Find(c => c.Id == id);
            vecchioC.Name = customer.Name;
            SalvaCustomerSuFile(_fileConfiguration.CustomersFile);
            return GetCustomerById(customer.Id);
        }
        throw new ArgumentOutOfRangeException(nameof(customer), "Customer non trovato");
    }

    public List<Customer> GetCustomers()
    {
        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        return Customers;
    }

    public Customer GetCustomerById(int id)
    {
        CaricaCustomerDaFile(_fileConfiguration.CustomersFile);
        return Customers.FirstOrDefault(c => c.Id == id);
    }
    
    static void CaricaCustomerDaFile(string percorsoFile)
    {
        if (File.Exists(percorsoFile))
        {
            try
            {
                string json = File.ReadAllText(percorsoFile);
                Customers = JsonConvert.DeserializeObject<List<Customer>>(json) ?? new List<Customer>();
                Console.WriteLine("Customer caricati con successo!");
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
    
    static void SalvaCustomerSuFile(string percorsoFile)
    {
        try
        {
            string json = JsonConvert.SerializeObject(Customers, Formatting.Indented);
            File.WriteAllText(percorsoFile, json);
            Console.WriteLine("Customers salvati con successo!");
            CaricaCustomerDaFile(percorsoFile);
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Errore durante il salvataggio dei customers: {ex.Message}");
        }
    }
}