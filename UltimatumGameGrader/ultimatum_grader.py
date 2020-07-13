# Given file of tab-separated strategies specified by each student,
# calculate each student's Player 1 and Player 2 payouts played against
# the strategies of every other student. (Names have been changed to randomized
# pseudonyms)

# By Yichen Zhang

class Player:
    def __init__(self, first_name, last_name, strategy1, strategy2):
        self.first_name = first_name
        self.last_name = last_name
        self.strategy1 = strategy1
        self.strategy2 = strategy2
        self.full_name = first_name + " " + last_name

def ultimatum_filepull(filename, split_amount):
    input_file = open(filename)
    player_list = []

    for line in input_file:
        current_line = line.strip().split("\t")
        # print(current_line) # To check that everything split correctly
        # Get the student name
        full_name = current_line[0].split(" ")
        first_name = full_name[0]
        last_name = full_name[1]
        # Get the student's strategy as Player 1
        keep_amt = int(current_line[1][1])
        offer_amt = int(split_amount) - keep_amt
        strategy1 = (keep_amt, offer_amt)
        # Get the student's strategy as Player 2
        strategy2 = []
        for decision in current_line[2:]:
            strategy2.append(decision)
        # Now create the Player object for each student
        player_list.append(Player(first_name, last_name, strategy1, strategy2))

    input_file.close()

    player_list.sort(key=lambda x: x.last_name)
    return player_list

def ultimatum_grader(player_list):
    graded_dict = {}
    ave_dict = {}
    result_list = []

    for i in range(len(player_list) - 1):
        player_name = player_list[i].full_name
        as_player1 = player_list[i].strategy1
        as_player2 = player_list[i].strategy2
        for j in range(i+1, len(player_list)):
            opponent = player_list[j].full_name
            opponent_player2 = player_list[j].strategy2
            opponent_player1 = player_list[j].strategy1
            # A game with i as Player 1 and j as Player 2
            player_payout1, opponent_payout2 = 0, 0
            if opponent_player2[as_player1[0]] == "Accept":
                player_payout1 = as_player1[0]
                opponent_payout2 = as_player1[1]
            # A game with j as Player 1 and i as Player 2
            player_payout2, opponent_payout1 = 0, 0
            if as_player2[opponent_player1[0]] == "Accept":
                opponent_payout1 = opponent_player1[0]
                player_payout2 = opponent_player1[1]

            # Now add the game results to the dictionary by student name
            if player_name not in graded_dict:
                graded_dict[player_name] = [0, 0]
            else:
                graded_dict[player_name][0] += player_payout1
                graded_dict[player_name][1] += player_payout2
            if opponent not in graded_dict:
                graded_dict[opponent] = [0, 0]
            else:
                graded_dict[opponent][0] += opponent_payout1
                graded_dict[opponent][1] += opponent_payout2

        # Now to take the averages
        for student in graded_dict:
            total1 = graded_dict[student][0]
            total2 = graded_dict[student][1]
            ave_dict[student] = (round(total1 / len(player_list), 2), round(total2 / len(player_list), 2))

        #for student in ave_dict:
            #result_list.append([student, ave_dict[student]])
    #return result_list
    return ave_dict
# Test to make sure this works
# print(ultimatum_filepull("game_file.tsv", 4))

# Update with your tab-separated file name (can also just name the file game_file). No need to change the "pot" amount of 4
student_list = ultimatum_filepull("game_file.tsv", 4)
print(ultimatum_grader(student_list))
