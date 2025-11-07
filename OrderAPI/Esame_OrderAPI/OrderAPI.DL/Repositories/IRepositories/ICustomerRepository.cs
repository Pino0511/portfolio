using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.DL.Repositories.IRepositories;

public interface ICustomerRepository
{
    public Customer AddCustomer(Customer customer);
    
    public bool ExistById(int id);
    
    public bool DeleteCustomer(int id);
    
    public Customer UpdateCustomer(Customer customer, int id);
    
    public List<Customer> GetCustomers();
    
    public Customer GetCustomerById(int id);
}