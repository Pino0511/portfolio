using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services.IServices;

public interface IServiceOrder
{
    public Order AddOrder(Order order);
    
    public List<Order> GetOrders();
    
    public Order GetOrder(int id);
    
    public Order UpdateOrder(Order order, int id);
    
    public bool DeleteOrder(int id);
}