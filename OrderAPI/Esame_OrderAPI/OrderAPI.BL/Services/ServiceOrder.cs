using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services.IServices;

public class ServiceOrder : IServiceOrder
{
    
    IOrderRepository _OrderRepository;

    public ServiceOrder(IOrderRepository orderRepository) {
        _OrderRepository =  orderRepository;
    }
    
    public Order AddOrder(Order order)
    {
        _OrderRepository.AddOrder(order);
        return _OrderRepository.GetOrderById(order.Id);
    }

    public List<Order> GetOrders()
    {
        return _OrderRepository.GetOrders();
    }

    public Order GetOrder(int id)
    {
        return _OrderRepository.GetOrderById(id);
    }

    public Order UpdateOrder(Order order, int id)
    {
        return _OrderRepository.UpdateOrder(order, id);
    }

    public bool DeleteOrder(int id)
    {
        return _OrderRepository.DeleteOrder(id);
    }
}