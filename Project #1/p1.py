# Zahir Choudhry & Brian Cuellar
# Project 1 - Find Week Day of Birth
# 9/10/21

from math import floor


def is_leap_year(year):
    """
    if (year is not divisible by 4) then (it is a common year)
else if (year is not divisible by 100) then (it is a leap year)
else if (year is not divisible by 400) then (it is a common year)
else (it is a leap year)
    """

    # WORKS

    lp_status = True


    if year % 4 != 0:
       lp_status = False

    elif (year % 100 != 0):
        lp_status = True

    elif (year % 400 != 0):
        lp_status = False
    else:
        lp_status = True

    return lp_status

    pass # FIXME


def is_gregorian_date(year, month, date):

    # WORKS

    if (year > 1752):
        return True
    elif(year == 1752 and month > 9):
        return True
    elif(year == 1752 and month == 9 and date > 13):
        return True
    else :
        return False

    pass # FIXME


def is_valid_date(year, month, date):

    #WORKS

    if (year >= 1752):
        if (year == 1752 and month <= 9 and date < 14):
            return False
        if( month >= 1 and month <= 12):
            # Jan, Mar, May, July, Aug, Oct, Dec - 31 Days
            if month == 1 or month == 3 or month == 5 or month == 7 or month == 8 or month == 10 or month == 12:
                if (date >= 1 and date <= 31):
                    #print("Taco")
                    return True
            # April, June, September, November - 30 days
            elif month == 4 or month == 6 or month == 9 or month == 11:
                if date >= 1 and date <= 30:
                    #print("Burrito")
                    return True
            #February - Account for Leap Year
            elif month == 2:
                if is_leap_year(year) == True:
                    if(date >= 1 and date <= 29):
                        #print("Cheeto")
                        return True
                elif is_leap_year(year) == False:
                    if(date >= 1 and date <= 28):
                        #print("Brocolli")
                        return True
        return False

    pass # FIXME


def weekday_of(year, month, date):

    day_of_week = None

    #WIP
    if (is_gregorian_date(year, month, date) == False):
        print("Error, not part of Gergorian Calender.\nMust be a after 1752--09--13.")
        return
    if (is_valid_date(year, month, date) == False):
        print("Error, either the month, day or year input is invalid.")
        return

    # Parts of Zeller's Congruence:

    # print(str(year) + " is the year\n")

    if (month <= 2): # NEW ADDITION
        month += 12
        year -= 1

    p_1 = floor( ( 13 * (month+1) ) / 5 ) # 1st part of equation
    # print(str(p_1) + " P_1")

    p_2 = year % 100 # K = year % 100
    # print(str(p_2) + " P_2")

    p_3 = floor( (year % 100) / 4 ) # floor( K/4 )
    # print(str(p_3) + " P_3")

    p_4 = floor( year / 400 ) # floor(J/4)
    # print(str(p_4) + " P_4")

    p_5 = 5 * floor( year / 100 )
    # print(str(p_5) + " P_5")
    day_of_week = (date + p_1 + p_2 + p_3 + p_4 + p_5) % 7

    return day_of_week
    pass # FIXME


def weekday_name(weekday):

    # WORKS

    week = ["Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]

    return week[weekday]
    pass # FIXME


def main():
    birthday = input("Enter your birthday in YYYY-MM-DD format:")
    year = int(birthday[0:4])  # YYYY
    month = int(birthday[5:7])  # MM
    day = int(birthday[8:10])  # DD

    if (is_gregorian_date(year, month, day) == False):
        print("The date you entered is invalid.")
        #print("Error, not part of Gergorian Calender.\nMust be a after 1752--09--13.")
        return
    elif (is_valid_date(year, month, day) == False):
        print("The date you entered is invalid.")
        # print("Error, either the month, day or year input is invalid.")
        return

    week_num = weekday_of(year, month, day)

    weekday_fin = weekday_name(week_num)

    print("You were born on a " + weekday_fin + "!")


"""
    The main entry point for this program

    This program should ask the user for their birthday, then check if the date
    is valid. If it is, the program should print the day of the week of the
    user's birthday. Otherwise, tell the user that their date is invalid.

    Example run 1:

		Enter your birthday in YYYY-MM-DD format: 1887-04-20
		You were born on a Wednesday!

    Example run 2:

		Enter your birthday in YYYY-MM-DD format: 1961-08-04
		You were born on a Friday!

    Example run 3:

		Enter your birthday in YYYY-MM-DD format: 1215-06-15
		The date you entered is invalid.

    Example run 4:

		Enter your birthday in YYYY-MM-DD format: 2013-17-42
		The date you entered is invalid.
"""
# pass FIXME


if __name__ == '__main__':
    main()
