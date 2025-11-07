using System.ComponentModel.DataAnnotations;

namespace Esame_OrderAPI.Models.Dto
{
    public class Order
    {
        public int Id { get; set; }

        public decimal Total { get; set; }
        public DateTime CreatedAt { get; set; } = DateTime.Now;
        public int CustomerId { get; set; }
    }
}
