(**
 * Crab Cups parts 1 and 2 in Pascal (derived from C solution).
 *
 * Build with 'fpc -Mdelphi puzzle.pas' to have 32 bits Integer type. Reduce
 * MaxCups, MaxRounds and Progress constants to compile with Turbo Pascal 3. 
 *)
program CrabCups;

type
  MyString = String[9];                 (* To make Turbo Pascal happy. *)

const
  MaxCups   = 1000000;                  (* Number of cups in part 2.   *)
  MaxRounds = 10000000;                 (* Number of rounds in part 2. *)
  Progress  = 10000;                    (* Progress indicator steps.   *)

var
  Total: Integer;                       (* Total number of cups.       *)
  Current: Integer;                     (* Number of current cup.      *)
  Next: Array[1..MaxCups] of Integer;   (* Neighbors of each cup.      *)
    
(**
 * Sets neighbor of cup i to j.
 *)
procedure SetNext(I, J: Integer);
begin
  Next[I] := J;
end;

(**
 * Gets neighbor of cup i.
 *)
function GetNext(I: Integer): Integer;
begin
  GetNext := Next[I];
end;

(**
 * Starts a new game with the given start configuration and a given total number
 * of cups.
 *)
procedure Init(Start: MyString; Cups: Integer);
var
  I, J, K: Integer;
begin
  Total := Cups;

  Current := Ord(Start[1]) - Ord('0');

  (* Link start cups with each other. *)
  J := Current;
    
  for I := 2 to Length(Start) do
  begin
    K := Ord(Start[I]) - Ord('0');
    WriteLn(J, ' -> ', K);
    SetNext(J, K);
    J := K;
  end;

  (* Link remaining cups (if any). *)
  WriteLn('[....]');
  for I := Length(Start) + 1 to Cups do
  begin
    (* WriteLn(J, ' -> ', K); *)
    SetNext(J, I);
    J := I;
  end;

  (* Link last cup with first in start configuration. *)
  WriteLn(Cups, ' -> ', Current);
  SetNext(J, Current);

  WriteLn;
end;
   
(**
 * Plays the given number of rounds, wildly shuffling and swapping our cups.
 *)
procedure Play(Rounds: Integer);
var
  Hand, Dest: Integer;
begin
  while Rounds > 0 do
  begin
    Rounds := Rounds - 1;

    (* Pick *)
    Hand := GetNext(Current);
    SetNext(Current, GetNext(GetNext(GetNext(Hand))));

    (* Place *)
    Dest := Current;
    repeat
      Dest := Dest - 1;
      if Dest = 0 then Dest := Total;
    until (Dest <> Hand) and (Dest <> GetNext(Hand)) and (Dest <> GetNext(GetNext(Hand)));

    SetNext(GetNext(GetNext(Hand)), GetNext(Dest));
    SetNext(Dest, Hand);

    (* Rotate *)
    Current := GetNext(Current);

    if Rounds mod Progress = 0 then Write('#');
  end;

  WriteLn;
  WriteLn;
end;

(**
 * Prints the solution (neighbors of cup 1, product of first and second).
 *)
procedure Done;
var
  Cup1, Cup2, I: Integer;
  Product: Real;
begin  
  Cup1 := GetNext(1);
  Cup2 := GetNext(Cup1);

  Product := Cup1 * Cup2; (* Caution: Large value! *)

  Write('Neighbors of 1 = ');
  for I := 1 to 9 do begin
    Write(Cup1, ' ');
    Cup1 := GetNext(Cup1);
  end;
  WriteLn;

  WriteLn('First * second = ', product:0:0);
  WriteLn;
end;
    
begin
  WriteLn('*** AoC 2020.23 Crab Cups ***');
  WriteLn;

  WriteLn('--- Part 1 ---');
  WriteLn;

  Init('398254716', 9);
  Play(100);
  Done;

  WriteLn('--- Part 2 ---');
  WriteLn;

  Init('398254716', MaxCups);
  Play(MaxRounds);
  Done;
end.
