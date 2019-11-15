Feature: Test geoSuggest API



  Scenario Outline: As a user, want to query about a specific city
    Given the user want querying about <city> city
    When hitting the api with this place
    Then a success response should be returned with proper data

    Examples:
    |city|
    |paris|
    |amman|
    |dubai|

  Scenario Outline: As a user, want to query about a specific country
    Given the user want querying about <country> country
    When hitting the api with this place
    Then a success response should be returned with proper data

    Examples:
      |country|
      |Jordan|
      |Palestine|
      |India|

  Scenario Outline: As a user, want to query about a specific country by its abbreviation
    Given the user want querying about <abbr> abbreviation
    When hitting the api with this place
    Then a success response should be returned with proper data

    Examples:
      |abbr|
      |UAE|
      |KSA|
      |USA|
      |GB|

  Scenario: As a user, want to query about with empty location
    Given the user want querying with empty location
    When hitting the api with this place
    Then a failure response should be returned with proper data

  Scenario: As a user, want to query about by sending the location as null
    Given the user want querying with null location
    When hitting the api with this place
    Then a failure response should be returned with proper data

 Scenario Outline: As a user, want to query about by sending the location as invalid location
    Given the user want querying with <invalidLocation> as location
    When hitting the api with this place
    Then a failure response should be returned with proper data

    Examples:
   |invalidLocation|
   |1235436        |
   |@#%#$^%        |
   |hdjfg76463     |
   |iuer#$         |
   |367hggd$%$^    |
