using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CustomerController : ControllerBase
    {
        private readonly IServiceCustomer _customerService;

        public CustomerController(IServiceCustomer customerService)
        {
            _customerService = customerService;
        }

        [HttpGet("/api/customers")]
        [AllowAnonymous]  //GET anonimi
        public List<Customer> GetAllCustomers()
        {
            return _customerService.GetAllCustomers();
        }

        [HttpGet("/api/customers/{id}")]
        [AllowAnonymous]  // idem
        public Customer GetCustomerById([FromRoute] int id)
        {
            return _customerService.GetCustomerById(id);
        }

        [HttpPost("/api/customers")]
        public Customer AddCustomer([FromBody] Customer customer)
        {
            return _customerService.AddCustomer(customer);
        }

        [HttpPut("/api/customers/{id}")]
        public Customer UpdateCustomer([FromBody] Customer customer, [FromRoute] int id)
        {
            return _customerService.UpdateCustomer(customer, id);
        }

        [HttpDelete("/api/customers/{id}")]
        public bool DeleteCustomer(int id)
        {
            return _customerService.DeleteCustomer(id);
        }
    }
}
