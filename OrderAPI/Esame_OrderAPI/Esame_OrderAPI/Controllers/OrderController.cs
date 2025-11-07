using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.Models.Dto;
using System.Collections.Generic;

namespace Esame_OrderAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class OrderController : ControllerBase
    {
        private readonly IServiceOrder _orderService;

        public OrderController(IServiceOrder orderService)
        {
            _orderService = orderService;
        }

        [HttpGet("/api/orders")]
        [AllowAnonymous]
        public List<Order> GetAllOrders()
        {
            return _orderService.GetOrders();
        }

        [HttpGet("/api/orders/{id}")]
        [AllowAnonymous]
        public Order GetOrderById([FromRoute] int id)
        {
            return _orderService.GetOrder(id);
        }

        [HttpPost("/api/orders")]
        public Order AddOrder([FromBody] Order order)
        {
            return _orderService.AddOrder(order);
        }

        [HttpPut("/api/orders/{id}")]
        public Order UpdateOrder([FromBody] Order order, [FromRoute] int id)
        {
            return _orderService.UpdateOrder(order, id);
        }

        [HttpDelete("/api/orders/{id}")]
        public bool DeleteOrder(int id)
        {
            return _orderService.DeleteOrder(id);
        }
    }
}
