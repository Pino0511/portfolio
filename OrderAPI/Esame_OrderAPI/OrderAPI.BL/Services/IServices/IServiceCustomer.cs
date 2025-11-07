using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services.IServices;

public interface IServiceCustomer
{
    public  Customer AddCustomer(Customer customer);
    
    public List<Customer> GetAllCustomers();
    
    public Customer GetCustomerById(int id);
    
    public Customer UpdateCustomer(Customer customer, int id);
    
    public bool DeleteCustomer(int id);
}