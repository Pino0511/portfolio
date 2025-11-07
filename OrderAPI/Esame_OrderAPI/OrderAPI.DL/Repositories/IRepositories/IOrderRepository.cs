using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.DL.Repositories.IRepositories;

public interface IOrderRepository
{
    public Order AddOrder(Order order);
    
    public bool ExistById(int id);
    
    public bool DeleteOrder(int id);
    
    public Order UpdateOrder(Order order, int id);
    
    public List<Order> GetOrders();
    
    public Order GetOrderById(int orderId);
}