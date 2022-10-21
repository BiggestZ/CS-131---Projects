#!/usr/bin/env python3
# Zahir Choudhry
# Social Network

class SocialNetwork:

    # WORKS
    def __init__(self):
        self.users = {}

    # WORKS
    def list_users(self):
        return self.users.keys()  # Returns the entire array

    # WORKS
    def add_user(self, user):
        if (user not in self.users):    #Checks if user is in the network already. If not, it is appended.
            self.users[user] = [] #Creates a list for friends #Sets the Dictionary value to the list
               # Creates an empty friend list for new user

    # WORKS
    def add_friend(self, user, friend):
        if (user not in self.users.keys()):    #Checks if user is in the network already. If not, it is appended.
            self.add_user(user)
        if (friend not in self.users.keys()): # Checks if friend is already in the network. If not, they are appended.
            self.add_user(friend)
        if (friend not in self.users[user]): # If the friend is not already friend of user, they are appended.
            self.users[user].append(friend) #Should append to the list inside the Dictionary

    # WORKS
    def get_friends(self, user):
        return self.users[user] # Returns the list of friends of specified user.

    # Function to return the key value in a dictionary based on a value
    def get_key(self, val, dict):
            for key, value in dict.items():
                if val == value:
                    return key

    # WORKS
    def suggest_friend(self, user):
        
        j_dictionary = {} # Creates an empty dictionary to hold jaccard values

        list1 = self.get_friends(user) # Takes the friend list of the user
        list2 = [] # An empty list that will be assigned to the friends of users being tested

        for key in self.users.keys():
            if key != user:

                f_common = 0 # Friends in Common
                t_friends = 0 # Total # of friends between 2 lists

                list2 = self.get_friends(key) # Assigns "key"'s friends to list2
                
                t_friends = len(list1) + len(list2) # Initializes length to be total # of people between both
                print(str(t_friends) + " 11111111")

                for f1 in list2:    # For 
                    for f2 in list1:
                        if f1 == f2:
                            f_common += 1
                t_friends -= f_common   # makes length of list of total subtracting people they have in common
                
                j_dictionary[key] = f_common / t_friends # assigns the value to the username of the friend
        return self.get_key(max(j_dictionary.values()), j_dictionary) 

    # Functions past this point are given to us
    def to_dot(self):
        result = []
        result.append('digraph {')
        result.append('    layout=neato')
        result.append('    overlap=scalexy')
        for user in self.list_users():
            for friend in self.get_friends(user):
                result.append('    "{}" -> "{}"'.format(user, friend))
        result.append('}')
        return '\n'.join(result)

def create_network_from_file(filename):
    network = SocialNetwork()
    with open(filename) as fd:
        for line in fd.readlines():
            line = line.strip()
            users = line.split()
            network.add_user(users[0])
            for friend in users[1:]:
                network.add_friend(users[0], friend)
    return network

def main():
    network = create_network_from_file('simple.network.txt')
    print(network.to_dot())
    print(network.suggest_friend('francis'))

if __name__ == '__main__':
    main()