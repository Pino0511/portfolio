using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services;

public class ServiceCustomer : IServiceCustomer
{
    
    ICustomerRepository _CustomerRepository;

    public ServiceCustomer(ICustomerRepository customerRepository) {
        _CustomerRepository =  customerRepository;
    }
    
    public Customer AddCustomer(Customer customer)
    {
        _CustomerRepository.AddCustomer(customer);
        return _CustomerRepository.GetCustomerById(customer.Id);
    }

    public List<Customer> GetAllCustomers()
    {
        return _CustomerRepository.GetCustomers();
    }

    public Customer GetCustomerById(int id)
    {
        return _CustomerRepository.GetCustomerById(id);
    }

    public Customer UpdateCustomer(Customer customer, int id)
    {
        return  _CustomerRepository.UpdateCustomer(customer, id);
    }

    public bool DeleteCustomer(int id)
    {
        return _CustomerRepository.DeleteCustomer(id);
    }
}