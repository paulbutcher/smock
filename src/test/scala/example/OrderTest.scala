package com.paulbutcher.smock.example

import org.scalatest.WordSpec
import com.paulbutcher.smock.MockFactory

class OrderTest extends WordSpec with MockFactory {
  
  "An order" when {
    "in stock" should {
      "remove inventory" in {
        val warehouse = mock[Warehouse]
        warehouse.expects('hasInventory).withArgs("Talisker", 50).returns(true).once
        warehouse.expects('remove).withArgs("Talisker", 50).once
        
        val order = new Order("Talisker", 50)
        order.fill(warehouse)
        
        assert(order.isFilled)
      }
    }
    
    "out of stock" should {
      "remove nothing" in {
        val warehouse = mock[Warehouse]
        warehouse.expects('hasInventory).returns(false).once
        
        val order = new Order("Talisker", 50)
        order.fill(warehouse)
        
        assert(!order.isFilled)
      }
    }
  }
}
