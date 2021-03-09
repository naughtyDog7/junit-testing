Feature: Bonus Policy
  The company follows bonus policy depending on the passenger level and mileage

  Scenario Outline: Standard Passenger Bonus Policy
    Given we have a standard passenger with mileage
    When passenger travels <mileage1> and <mileage2> and <mileage3> miles
    Then the bonus points should be <points>

    Examples:
      | mileage1 | mileage2 | mileage3 | points |
      | 349      | 319      | 623      | 64     |
      | 312      | 356      | 135      | 40     |
      | 223      | 786      | 503      | 75     |
      | 482      | 98       | 591      | 58     |
      | 128      | 176      | 304      | 30     |

  Scenario Outline: VIP Passenger with mileage
    Given we have a VIP passenger with mileage
    When passenger travels <mileage1> and <mileage2> and <mileage3> miles
    Then the bonus points should be <points>

    Examples:
      | mileage1 | mileage2 | mileage3 | points |
      | 349      | 319      | 623      | 129    |
      | 312      | 356      | 135      | 80     |
      | 223      | 786      | 503      | 151    |
      | 482      | 98       | 591      | 117    |
      | 128      | 176      | 304      | 60     |